package dke.pr.cli;

import java.io.IOException;
import java.util.List;

/**
 * Specific wrapper for CBR
 * 
 * @author fburgstaller
 *
 */
public class CBRInterface extends dke.pr.cli.Flora2CLI {

	protected static final String MODEL_MODULE = "ctxModel";
	protected static final String BC_MODULE = "bc";
	private final String CONTEXT_CLASS;
	private final String BC_CLASS;

	/**
	 * Initializes a Flora 2 shell with the CBR Model and the business cases,
	 * the contextClass specifies the name of the context class used (AIMCtx)
	 * 
	 * @param fCtxModel
	 * @param fBc
	 * @param contextClass
	 * @throws IOException
	 */
	public CBRInterface(String fCtxModel, String fBc, String contextClass,
			String bcClass) throws IOException {
		super();
		this.CONTEXT_CLASS = contextClass;
		this.BC_CLASS = bcClass;
		if (!loadFile(fCtxModel, MODEL_MODULE))
			throw new IOException("Loading module failed");
		if (!loadFile(fBc, BC_MODULE))
			throw new IOException("Loading module failed");
	}

	// -----------------------------------------------------------------------------
	// ------------------ Query model information
	// -----------------------------------------------------------------------------
	/**
	 * Retrieve the contexts in the model
	 * 
	 * @return a list of contexts
	 * @throws IOException
	 */
	public List<String> getCtxs() throws IOException {
		String cmd = String.format("?ctx:%s@%s.", CONTEXT_CLASS, MODEL_MODULE);
		String ret = issueCommand(cmd);
		return parseSingleVar(ret);
	}

	/**
	 * Retrieves all super-sub context tuples of the model
	 * 
	 * @return List of context-pairs [subCtx,superCtx]
	 * @throws IOException
	 */
	public List<String[]> getCtxHierarchy() throws IOException {
		String cmd = String
				.format("?subCtx:%s[specialises->?superCtx]@%s, \\+ (?superCtx =?subCtx).",
						CONTEXT_CLASS, MODEL_MODULE);
		String ret = issueCommand(cmd);
		return parseMultipleVars(ret, 2);
	}

	/**
	 * Get the file location of the rule file belonging to context ctx
	 * 
	 * @param ctx
	 * @return file path of rule file
	 * @throws IOException
	 */
	public String getCtxFile(String ctx) throws IOException {
		String cmd = String.format("%s:%s[file->?ctxf]@%s.", ctx,
				CONTEXT_CLASS, MODEL_MODULE);
		String ret = issueCommand(cmd);
		return parseSingleVar(ret).get(0);
	}

	/**
	 * @param ctx
	 * @return
	 * @throws IOException
	 */
	public List<String[]> getCtx(String ctx) throws IOException {
		String cmd = String.format("(%s:%s[?val:Parameter->?ctx])@%s.", ctx, CONTEXT_CLASS,
				MODEL_MODULE);
		String ret = issueCommand(cmd);
		return parseMultipleVars(ret,2);
	}

	/**
	 * Get the parameters of the CBR model
	 * 
	 * @return list of parameters
	 * @throws IOException
	 */
	public List<String> getParameters() throws IOException {
		String cmd = String.format("?param:Parameter@%s.", MODEL_MODULE);
		String ret = issueCommand(cmd);
		return parseSingleVar(ret);
	}

	/**
	 * Get all parameter values of the CBR model
	 * 
	 * @return
	 * @throws IOException
	 */
	public List<String> getParameterValues() throws IOException {
		String cmd = String.format("(?val:?_param,?_param:Parameter)@%s.",
				MODEL_MODULE);
		String ret = issueCommand(cmd);
		return parseSingleVar(ret);
	}

	/**
	 * Gets the Parameter Value Hierarchy for a specific Parameter
	 * Retrieves all super-sub parameter value tuples of the model
	 * 
	 * @parameter parameter name
	 * @return List of parameter-value-pairs [superValue,subValue]
	 * @throws IOException
	 */
	public List<String[]> getParameterValuesHiearchy(String parameter)
			throws IOException {
		String cmd = String
				.format("?superVal:%s[covers->?subVal:%s]@%s, \\+ (?superVal = ?subVal).",
						parameter, parameter, MODEL_MODULE);
		String ret = issueCommand(cmd);
		return parseMultipleVars(ret, 2);
	}

	// -----------------------------------------------------------------------------
	// ------------------ Evaluate business cases
	// -----------------------------------------------------------------------------
	/**
	 * Retrieve relevant contexts for given business case.
	 * 
	 * @param bc
	 * @return
	 * @throws IOException
	 */
	public List<String> detRelevantCtxs(String bc) throws IOException {
		String cmd = String.format("%s[detRelevantCtxs(%s)->?ctx]@%s.",
				CONTEXT_CLASS, bc, MODEL_MODULE);
		String ret = issueCommand(cmd);
		return parseSingleVar(ret);
	}

	/**
	 * Merges rules/terms of all contexts relevant to bc into the specified
	 * targetModule.
	 * 
	 * @param bc
	 * @param targetModule
	 * @return
	 * @throws IOException
	 */
	public List<String> detCaseSpecificCtx(String bc, String targetModule)
			throws IOException {
		checkTargetModule(targetModule);
		String cmd = String.format(
				"%s[%%detCleanCaseSpecificCtx(%s,%s,?ctxf)]@%s.",
				CONTEXT_CLASS, bc, targetModule, MODEL_MODULE);
		String ret = issueCommand(cmd);
		return parseSingleVar(ret);

	}

	/**
	 * creates a new business case
	 * 
	 * @param bcDef
	 * @return
	 * @throws IOException
	 */
	public boolean newBusinessCase(String bcDef) throws IOException {
		String cmd = String.format("%s[%%newBC(%s)]@%s.", BC_CLASS, bcDef,
				BC_MODULE);
		String ret = issueCommand(cmd);
		return ret.endsWith("Yes\n");

	}

	/**
	 * creates/empties the target module
	 * 
	 * @param targetModule
	 * @return
	 * @throws IOException
	 */
	private boolean checkTargetModule(String targetModule) throws IOException {
		String cmd = String
				.format("\\if isloaded{%s} \\then erasemodule{%s} \\else newmodule{%s}.",
						targetModule, targetModule, targetModule);
		String ret = issueCommand(cmd);
		return ret.equals("Yes\n");
	}

	// -----------------------------------------------------------------------------
	// -------------------- Modification operations
	// -----------------------------------------------------------------------------

	// ---------------context
	/**
	 * Adds the flora2 context spec to the model
	 * 
	 * @param ctxDef
	 * @return whether it works or not
	 * @throws IOException
	 */
	public boolean addCtx(String ctxDef) throws IOException {
		String cmd = String.format("%s[%%addCtx(%s)]@%s.", CONTEXT_CLASS,
				ctxDef, MODEL_MODULE);
		String ret = issueCommand(cmd);
		return ret.equals("Yes\n");
	}

	/**
	 * Deletes the context with the name ctx from the model
	 * 
	 * @param ctx
	 * @return
	 * @throws IOException
	 */
	public boolean delCtx(String ctx) throws IOException {
		String cmd = String.format("%s[%%delCtx(%s)]@%s.", CONTEXT_CLASS, ctx,
				MODEL_MODULE);
		String ret = issueCommand(cmd);
		return ret.equals("Yes\n");
	}

	/**
	 * Deletes all contexts refering to a non-existing parameter value
	 * 
	 * @return
	 * @throws IOException
	 */
	public boolean delCtxByParameterValue() throws IOException {
		String cmd = String.format("%s[%%delCtxByValue]@%s.", CONTEXT_CLASS,
				MODEL_MODULE);
		String ret = issueCommand(cmd);
		return ret.endsWith("Yes\n");
	}

	/**
	 * Changes all contexts refering to a non-existing parameter value
	 * (oldParamValue) to the new parameter value
	 * 
	 * @param oldParamValue
	 * @param newParamValue
	 * @return
	 * @throws IOException
	 */
	public boolean modifyCtxByParameterValue(String oldParamValue,
			String newParamValue) throws IOException {
		String cmd = String.format("%s[%%modCtxByValue(%s,%s,?ctx)]@%s.",
				CONTEXT_CLASS, oldParamValue, newParamValue, MODEL_MODULE);
		String ret = issueCommand(cmd);
		return ret.endsWith("Yes\n");
	}

	// ---------------parameter
	/**
	 * add a parameter with name and parameter root value rootValue and the
	 * detParam method specified in Flora2 in parameter detParamDef
	 * 
	 * @param name
	 * @param rootValue
	 * @param detParamDef
	 * @return
	 * @throws IOException
	 */
	public boolean addParameter(String name, String rootValue,
			String detParamDef) throws IOException {
		String cmd = String.format("%s[%%addParam(%s,%s,?ctx,%s)]@%s.",
				CONTEXT_CLASS, name, rootValue, detParamDef, MODEL_MODULE);
		String ret = issueCommand(cmd);
		return ret.endsWith("Yes\n");
	}

	/**
	 * Deletes the parameter name
	 * 
	 * @param name
	 * @return
	 * @throws IOException
	 */
	public boolean delParameter(String name) throws IOException {
		String cmd = String.format("%s[%%delParam(%s,?ctx)]@%s.",
				CONTEXT_CLASS, name, MODEL_MODULE);
		String ret = issueCommand(cmd);
		return ret.endsWith("Yes\n");
	}

	// ---------------parameter value
	/**
	 * Adds a parameter leaf value under the value parent
	 * 
	 * @param name
	 * @param parent
	 * @return
	 * @throws IOException
	 */
	public boolean addParameterValueLeaf(String name, String parent)
			throws IOException {
		String cmd = String.format("%s[%%addValueLeaf(%s,%s)]@%s.",
				CONTEXT_CLASS, name, parent, MODEL_MODULE);
		String ret = issueCommand(cmd);
		return ret.endsWith("Yes\n");
	}

	/**
	 * Add a parameter value in the parameter value tree between parent and
	 * children
	 * 
	 * @param name
	 * @param parent
	 * @param children
	 * @return
	 * @throws IOException
	 */
	public boolean addParameterValueNode(String name, String parent,
			String[] children) throws IOException {
		StringBuilder childs = new StringBuilder(80);
		for (String child : children) {
			childs.append(child + ",");
		}
		childs.delete(childs.length() - 1, childs.length());
		String cmd = String.format("%s[%%addValueNode(%s,%s,{%s})]@%s.",
				CONTEXT_CLASS, name, parent, childs, MODEL_MODULE);
		String ret = issueCommand(cmd);
		return ret.endsWith("Yes\n");
	}

	/**
	 * Add a new rootValue above child
	 * 
	 * @param name
	 * @param child
	 * @return
	 * @throws IOException
	 */
	public boolean addParameterValueRoot(String name, String child)
			throws IOException {
		String cmd = String.format("%s[%%addValueRoot(%s,%s)]@%s.",
				CONTEXT_CLASS, name, child, MODEL_MODULE);
		String ret = issueCommand(cmd);
		return ret.endsWith("Yes\n");
	}

	/**
	 * Delete parameter value name and all its subvalues
	 * 
	 * @param name
	 * @return
	 * @throws IOException
	 */
	public boolean delParameterValueSubgraph(String name) throws IOException {
		String cmd = String.format("%s[%%delValueSubGraph(%s)]@%s.",
				CONTEXT_CLASS, name, MODEL_MODULE);
		String ret = issueCommand(cmd);
		return ret.endsWith("Yes\n");
	}

	/**
	 * Delete a single parameter value
	 * 
	 * @param name
	 * @return
	 * @throws IOException
	 */
	public boolean delParameterValue(String name) throws IOException {
		String cmd = String.format("%s[%%delValue(%s,?parent)]@%s.",
				CONTEXT_CLASS, name, MODEL_MODULE);
		String ret = issueCommand(cmd);
		return ret.endsWith("Yes\n");
	}

	/**
	 * Delete a single parameter value
	 * 
	 * @param name
	 * @return
	 * @throws IOException
	 */
	public List<String> detUnusedParameterValues() throws IOException {
		String cmd = String.format("%s[detUnusedValues->?v]@%s.",
				CONTEXT_CLASS, MODEL_MODULE);
		String ret = issueCommand(cmd);
		return parseSingleVar(ret);

	}
}
