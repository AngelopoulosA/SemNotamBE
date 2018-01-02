
:-(compiler_options([xpp_on,canonical])).

/********** Tabling and Trailer Control Variables ************/

#define EQUALITYnone
#define INHERITANCEflogic
#define TABLINGreactive
#define TABLINGvariant
#define CUSTOMnone

#define FLORA_INCREMENTAL_TABLING 

/************************************************************************
  file: headerinc/flrheader_inc.flh

  Author(s): Guizhen Yang

  This file is automatically included by the Flora-2 compiler.
************************************************************************/

:-(compiler_options([xpp_on])).
#mode standard Prolog

#include "flrheader.flh"
#include "flora_porting.flh"

/***********************************************************************/

/************************************************************************
  file: headerinc/flrheader_prog_inc.flh

  Author(s): Michael Kifer

  This file is automatically included by the Flora-2 compiler.
************************************************************************/

:-(compiler_options([xpp_on])).
#mode standard Prolog

#include "flrheader_prog.flh"

/***********************************************************************/

#define FLORA_COMPILATION_ID 1

/************************************************************************
  file: headerinc/flrheader2_inc.flh

  Author(s): Michael Kifer

  This file is automatically included by the Flora-2 compiler.
  It has files that must be included in the header and typically
  contain some Prolog statements. Such files cannot appear
  in flrheader.flh because flrheader.flh is included in various restricted
  contexts where Prolog statements are not allowed.

  NOT included in ADDED files (compiled for addition) -- only in LOADED
  ones and in trailers/patch
************************************************************************/

:-(compiler_options([xpp_on])).

#define TABLING_CONNECTIVE  :-

%% flora_tabling_methods is included here to affect preprocessing of
%% flrtable/flrhilogtable.flh dynamically
#include "flora_tabling_methods.flh"

/* note: inside flrtable.flh there are checks for FLORA_NONTABLED_DATA_MODULE
   that exclude tabling non-signature molecules
*/
#ifndef FLORA_NONTABLED_MODULE
#include "flrtable.flh"
#endif

/* if normal tabled module, then table hilog */
#if !defined(FLORA_NONTABLED_DATA_MODULE) && !defined(FLORA_NONTABLED_MODULE)
#include "flrhilogtable.flh"
#endif

#include "flrtable_always.flh"

#include "flrauxtables.flh"

%% include list of tabled predicates
#mode save
#mode nocomment "%"
#if defined(FLORA_FLT_FILENAME)
#include FLORA_FLT_FILENAME
#endif
#mode restore

/***********************************************************************/

/************************************************************************
  file: headerinc/flrdyna_inc.flh

  Author(s): Chang Zhao

  This file is automatically included by the Flora-2 compiler.
************************************************************************/

:-(compiler_options([xpp_on])).

#define TABLING_CONNECTIVE  :-

#include "flrdyndeclare.flh"

/***********************************************************************/

/************************************************************************
  file: headerinc/flrindex_P_inc.flh

  Author(s): Michael Kifer

  This file is automatically included by the Flora-2 compiler.
************************************************************************/

:-(compiler_options([xpp_on])).

#include "flrindex_P.flh"

/***********************************************************************/

#mode save
#mode nocomment "%"
#define FLORA_THIS_FILENAME  'ctxModelAIM.flr'
#mode restore
/************************************************************************
  file: headerinc/flrdefinition_inc.flh

  Author(s): Guizhen Yang

  This file is automatically included by the Flora-2 compiler.
************************************************************************/

#include "flrdefinition.flh"

/***********************************************************************/

/************************************************************************
  file: headerinc/flrtrailerregistry_inc.flh

  Author(s): Michael Kifer

  This file is automatically included by the Flora-2 compiler.
************************************************************************/

#include "flrtrailerregistry.flh"

/***********************************************************************/

/************************************************************************
  file: headerinc/flrrefreshtable_inc.flh

  Author(s): Michael Kifer

  This file is automatically included by the Flora-2 compiler.
************************************************************************/

:-(compiler_options([xpp_on])).

#include "flrrefreshtable.flh"

/***********************************************************************/

/************************************************************************
  file: headerinc/flrdynamic_connectors_inc.flh

  Author(s): Michael Kifer

  This file is automatically included by the Flora-2 compiler.
************************************************************************/

:-(compiler_options([xpp_on])).

#include "flrdynamic_connectors.flh"

/***********************************************************************/

/************************************************************************
  file: syslibinc/flrimportedcalls_inc.flh

  Author(s): Michael Kifer

  This file is automatically included by the FLORA-2 compiler.
************************************************************************/

%% Loads the file with all the import statements for predicates
%% that must be known everywhere

:-(compiler_options([xpp_on])).

#mode standard Prolog

#if !defined(FLORA_TERMS_FLH)
#define FLORA_TERMS_FLH
#include "flora_terms.flh"
#endif

?-(:(flrlibman,flora_load_library(FLLIBIMPORTEDCALLS))).

/***********************************************************************/

/************************************************************************
  file: headerinc/flrpatch_inc.flh

  Author(s): Guizhen Yang

  This file is automatically included by the Flora-2 compiler.
************************************************************************/

#include "flrexportcheck.flh"
#include "flrpatch.flh"
/***********************************************************************/

/************************************************************************
  file: headerinc/flropposes_inc.flh

  Author(s): Michael Kifer

  This file is automatically included by the Flora-2 compiler.
************************************************************************/

#include "flropposes.flh"

/***********************************************************************/

/************************************************************************
  file: headerinc/flrhead_dispatch_inc.flh

  Author(s): Michael Kifer

  This file is automatically included by the Flora-2 compiler.
************************************************************************/

:-(compiler_options([xpp_on])).

#include "flrhead_dispatch.flh"

/***********************************************************************/

/************************************************************************
  file: syslibinc/flrbtdbop_inc.flh

  Author(s): Guizhen Yang

  This file is automatically included by the FLORA-2 compiler.
************************************************************************/

:-(compiler_options([xpp_on])).

#mode standard Prolog

#if !defined(FLORA_TERMS_FLH)
#define FLORA_TERMS_FLH
#include "flora_terms.flh"
#endif

?-(:(flrlibman,flora_load_library(FLLIBBTDBOP))).

/***********************************************************************/

/************************************************************************
  file: syslibinc/flrbtdbop_inc.flh

  Author(s): Guizhen Yang

  This file is automatically included by the FLORA-2 compiler.
************************************************************************/

:-(compiler_options([xpp_on])).

#mode standard Prolog

#if !defined(FLORA_TERMS_FLH)
#define FLORA_TERMS_FLH
#include "flora_terms.flh"
#endif

?-(:(flrlibman,flora_load_library(FLLIBBTDBOP))).

/***********************************************************************/

/************************************************************************
  file: syslibinc/flrbtdbop_inc.flh

  Author(s): Guizhen Yang

  This file is automatically included by the FLORA-2 compiler.
************************************************************************/

:-(compiler_options([xpp_on])).

#mode standard Prolog

#if !defined(FLORA_TERMS_FLH)
#define FLORA_TERMS_FLH
#include "flora_terms.flh"
#endif

?-(:(flrlibman,flora_load_library(FLLIBBTDBOP))).

/***********************************************************************/

/************************************************************************
  file: syslibinc/flrclause_inc.flh

  Author(s): Chang Zhao

  This file is automatically included by the FLORA-2 compiler.
************************************************************************/

:-(compiler_options([xpp_on])).

#mode standard Prolog

#if !defined(FLORA_TERMS_FLH)
#define FLORA_TERMS_FLH
#include "flora_terms.flh"
#endif

?-(:(flrlibman,flora_load_library(FLLIBCLAUSE))).

/***********************************************************************/

/************************************************************************
  file: syslibinc/flrdbop_inc.flh

  Author(s): Guizhen Yang

  This file is automatically included by the FLORA-2 compiler.
************************************************************************/

:-(compiler_options([xpp_on])).

#mode standard Prolog

#if !defined(FLORA_TERMS_FLH)
#define FLORA_TERMS_FLH
#include "flora_terms.flh"
#endif

?-(:(flrlibman,flora_load_library(FLLIBDBOP))).

/***********************************************************************/

/************************************************************************
  file: syslibinc/flrdbop_inc.flh

  Author(s): Guizhen Yang

  This file is automatically included by the FLORA-2 compiler.
************************************************************************/

:-(compiler_options([xpp_on])).

#mode standard Prolog

#if !defined(FLORA_TERMS_FLH)
#define FLORA_TERMS_FLH
#include "flora_terms.flh"
#endif

?-(:(flrlibman,flora_load_library(FLLIBDBOP))).

/***********************************************************************/

/************************************************************************
  file: syslibinc/flrdynrule_inc.flh

  Author(s): Chang Zhao

  This file is automatically included by the FLORA-2 compiler.
************************************************************************/

:-(compiler_options([xpp_on])).

#mode standard Prolog

#if !defined(FLORA_TERMS_FLH)
#define FLORA_TERMS_FLH
#include "flora_terms.flh"
#endif

?-(:(flrlibman,flora_load_library(FLSYSRULEUPDATE))).

/***********************************************************************/

/************************************************************************
  file: syslibinc/flraggmin_inc.flh

  Author(s): Michael Kifer

  This file is automatically included by the FLORA-2 compiler.
************************************************************************/

:-(compiler_options([xpp_on])).

#mode standard Prolog

#if !defined(FLORA_TERMS_FLH)
#define FLORA_TERMS_FLH
#include "flora_terms.flh"
#endif

?-(:(flrlibman,flora_load_library(FLLIBHYPOTHETICAL))).

/***********************************************************************/

/************************************************************************
  file: syslibinc/flrcontrol_inc.flh

  Author(s): Michael Kifer
	     Guizhen Yang

  This file is automatically included by the FLORA-2 compiler.
************************************************************************/

:-(compiler_options([xpp_on])).

#mode standard Prolog

#if !defined(FLORA_TERMS_FLH)
#define FLORA_TERMS_FLH
#include "flora_terms.flh"
#endif

?-(:(flrlibman,flora_load_library(FLLIBCONTROL))).

/***********************************************************************/

/************************************************************************
  file: syslibinc/flrdbop_inc.flh

  Author(s): Guizhen Yang

  This file is automatically included by the FLORA-2 compiler.
************************************************************************/

:-(compiler_options([xpp_on])).

#mode standard Prolog

#if !defined(FLORA_TERMS_FLH)
#define FLORA_TERMS_FLH
#include "flora_terms.flh"
#endif

?-(:(flrlibman,flora_load_library(FLLIBDBOP))).

/***********************************************************************/

/************************************************************************
  file: syslibinc/flrclause_inc.flh

  Author(s): Chang Zhao

  This file is automatically included by the FLORA-2 compiler.
************************************************************************/

:-(compiler_options([xpp_on])).

#mode standard Prolog

#if !defined(FLORA_TERMS_FLH)
#define FLORA_TERMS_FLH
#include "flora_terms.flh"
#endif

?-(:(flrlibman,flora_load_library(FLLIBCLAUSE))).

/***********************************************************************/

/************************************************************************
  file: syslibinc/flrload_inc.flh

  Author(s): Guizhen Yang

  This file is automatically included by the FLORA-2 compiler.
************************************************************************/

:-(compiler_options([xpp_on])).

#mode standard Prolog

#if !defined(FLORA_TERMS_FLH)
#define FLORA_TERMS_FLH
#include "flora_terms.flh"
#endif

?-(:(flrlibman,flora_load_library(FLLIBLOAD))).

/***********************************************************************/

/************************************************************************
  file: syslibinc/flrdynmod_inc.flh

  Author(s): Guizhen Yang

  This file is automatically included by the FLORA-2 compiler.
************************************************************************/

:-(compiler_options([xpp_on])).

#mode standard Prolog

#if !defined(FLORA_TERMS_FLH)
#define FLORA_TERMS_FLH
#include "flora_terms.flh"
#endif

?-(:(flrlibman,flora_load_library(FLLIBDYNMOD))).

/***********************************************************************/

/************************************************************************
  file: syslibinc/flrtruthvalue_inc.flh

  Author(s): Michael Kifer

  This file is automatically included by the FLORA-2 compiler.
************************************************************************/

:-(compiler_options([xpp_on])).

#mode standard Prolog

#if !defined(FLORA_TERMS_FLH)
#define FLORA_TERMS_FLH
#include "flora_terms.flh"
#endif

?-(:(flrlibman,flora_load_library(FLLIBTRUTHVALUE))).

/***********************************************************************/

/************************************************************************
  file: syslibinc/flrmetaops_inh.flh

  Author(s): Michael Kifer

  This file is automatically included by the FLORA-2 compiler.
************************************************************************/

:-(compiler_options([xpp_on])).

#mode standard Prolog

#if !defined(FLORA_TERMS_FLH)
#define FLORA_TERMS_FLH
#include "flora_terms.flh"
#endif


?-(:(flrlibman,flora_load_library(FLLIBMETAOPS))).

/***********************************************************************/

/************************************************************************
  file: syslibinc/flrcontrol_inc.flh

  Author(s): Michael Kifer
	     Guizhen Yang

  This file is automatically included by the FLORA-2 compiler.
************************************************************************/

:-(compiler_options([xpp_on])).

#mode standard Prolog

#if !defined(FLORA_TERMS_FLH)
#define FLORA_TERMS_FLH
#include "flora_terms.flh"
#endif

?-(:(flrlibman,flora_load_library(FLLIBCONTROL))).

/***********************************************************************/

/************************************************************************
  file: libinc/flrio_inc.flh

  Author(s): Guizhen Yang

  This file is automatically included by the Flora-2 compiler.
************************************************************************/

:-(compiler_options([xpp_on])).

#mode standard Prolog

#if !defined(FLORA_TERMS_FLH)
#define FLORA_TERMS_FLH
#include "flora_terms.flh"
#endif

?-(':'(flrlibman,flora_load_system_module(FLSYSMODIO))).

/***********************************************************************/

 
#if !defined(FLORA_FDB_FILENAME)
#if !defined(FLORA_LOADDYN_DATA)
#define FLORA_LOADDYN_DATA
#endif
#mode save
#mode nocomment "%"
#define FLORA_FDB_FILENAME  'ctxModelAIM.fdb'
#mode restore
?-(:(flrutils,flora_loaddyn_data(FLORA_FDB_FILENAME,FLORA_THIS_MODULE_NAME,'fdb'))).
#else
#if !defined(FLORA_READ_CANONICAL_AND_INSERT)
#define FLORA_READ_CANONICAL_AND_INSERT
#endif
?-(:(flrutils,flora_read_canonical_and_insert(FLORA_FDB_FILENAME,FLORA_THIS_FDB_STORAGE))).
#endif

 
#if !defined(FLORA_FLM_FILENAME)
#if !defined(FLORA_LOADDYN_DATA)
#define FLORA_LOADDYN_DATA
#endif
#mode save
#mode nocomment "%"
#define FLORA_FLM_FILENAME  'ctxModelAIM.flm'
#mode restore
?-(:(flrutils,flora_loaddyn_data(FLORA_FLM_FILENAME,FLORA_THIS_MODULE_NAME,'flm'))).
#else
#if !defined(FLORA_READ_CANONICAL_AND_INSERT)
#define FLORA_READ_CANONICAL_AND_INSERT
#endif
?-(:(flrutils,flora_read_descriptor_metafacts_canonical_and_insert(ctxModelAIM,_ErrNum))).
#endif

 
#if !defined(FLORA_FLD_FILENAME)
#if !defined(FLORA_LOADDYN_DATA)
#define FLORA_LOADDYN_DATA
#endif
#mode save
#mode nocomment "%"
#define FLORA_FLD_FILENAME  'ctxModelAIM.fld'
#mode restore
?-(:(flrutils,flora_loaddyn_data(FLORA_FLD_FILENAME,FLORA_THIS_MODULE_NAME,'fld'))).
#else
#if !defined(FLORA_READ_CANONICAL_AND_INSERT)
#define FLORA_READ_CANONICAL_AND_INSERT
#endif
?-(:(flrutils,flora_read_canonical_and_insert(FLORA_FLD_FILENAME,FLORA_THIS_FLD_STORAGE))).
#endif

 
#if !defined(FLORA_FLS_FILENAME)
#if !defined(FLORA_LOADDYN_DATA)
#define FLORA_LOADDYN_DATA
#endif
#mode save
#mode nocomment "%"
#define FLORA_FLS_FILENAME  'ctxModelAIM.fls'
#mode restore
?-(:(flrutils,flora_loaddyn_data(FLORA_FLS_FILENAME,FLORA_THIS_MODULE_NAME,'fls'))).
#else
#if !defined(FLORA_READ_CANONICAL_AND_INSERT)
#define FLORA_READ_CANONICAL_AND_INSERT
#endif
?-(:(flrutils,flora_read_symbols_canonical_and_insert(FLORA_FLS_FILENAME,FLORA_THIS_FLS_STORAGE,_SymbolErrNum))).
#endif


%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% Rules %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

:-(FLORA_THIS_WORKSPACE(static^mvdsig)(__model,flapply(detRelevantCtxs,__domainCase),__model,'_$ctxt'(_CallerModuleVar,4,__newcontextvar1)),','('_$_$_flora''rule_enabled'(4,'ctxModel.flr',FLORA_THIS_MODULE_NAME),','(FLORA_THIS_WORKSPACE(d^isa)(__model,'ContextClass','_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar3,4)),FLORA_WORKSPACE(bc,d^isa)(__domainCase,'BusinessCaseClass','_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar4,4))))).
:-(FLORA_THIS_WORKSPACE(static^mvdcondef)(__model,flapply(detRelevantCtxs,__domainCase),0,*,__model,'_$ctxt'(_CallerModuleVar,4,__newcontextvar2)),','('_$_$_flora''rule_enabled'(4,'ctxModel.flr',FLORA_THIS_MODULE_NAME),','(FLORA_THIS_WORKSPACE(d^isa)(__model,'ContextClass','_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar3,4)),FLORA_WORKSPACE(bc,d^isa)(__domainCase,'BusinessCaseClass','_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar4,4))))).
:-(FLORA_THIS_WORKSPACE(static^mvdsig)(__model,flapply(detCaseSpecificCtx,__domainCase,__newdontcarevar1,__newdontcarevar2),__model,'_$ctxt'(_CallerModuleVar,6,__newcontextvar3)),','('_$_$_flora''rule_enabled'(6,'ctxModel.flr',FLORA_THIS_MODULE_NAME),','(','(FLORA_THIS_WORKSPACE(d^isa)(__model,'ContextClass','_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar5,6)),FLORA_WORKSPACE(bc,d^isa)(__domainCase,'BusinessCaseClass','_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar6,6))),fllibexecute_delayed_calls([__domainCase,__model],[__newdontcarevar7,__domainCase,__model])))).
:-(FLORA_THIS_WORKSPACE(static^mvdcondef)(__model,flapply(detCaseSpecificCtx,__domainCase,__newdontcarevar1,__newdontcarevar2),0,*,__model,'_$ctxt'(_CallerModuleVar,6,__newcontextvar4)),','('_$_$_flora''rule_enabled'(6,'ctxModel.flr',FLORA_THIS_MODULE_NAME),','(','(FLORA_THIS_WORKSPACE(d^isa)(__model,'ContextClass','_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar5,6)),FLORA_WORKSPACE(bc,d^isa)(__domainCase,'BusinessCaseClass','_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar6,6))),fllibexecute_delayed_calls([__domainCase,__model],[__newdontcarevar7,__domainCase,__model])))).
:-(FLORA_THIS_WORKSPACE(static^imvdsig)(__model,file,'\\string','_$ctxt'(_CallerModuleVar,8,__newcontextvar1)),','('_$_$_flora''rule_enabled'(8,'ctxModel.flr',FLORA_THIS_MODULE_NAME),','(','(FLORA_THIS_WORKSPACE(d^isa)(__model,'ContextClass','_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar9,8)),FLORA_THIS_WORKSPACE(d^mvd)(__model,defBy,__param,'_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar10,8))),fllibexecute_delayed_calls([__model,__param],[__model])))).
:-(FLORA_THIS_WORKSPACE(static^imvdcondef)(__model,file,0,1,'_$ctxt'(_CallerModuleVar,8,__newcontextvar2)),','('_$_$_flora''rule_enabled'(8,'ctxModel.flr',FLORA_THIS_MODULE_NAME),','(','(FLORA_THIS_WORKSPACE(d^isa)(__model,'ContextClass','_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar9,8)),FLORA_THIS_WORKSPACE(d^mvd)(__model,defBy,__param,'_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar10,8))),fllibexecute_delayed_calls([__model,__param],[__model])))).
:-(FLORA_THIS_WORKSPACE(static^imvdsig)(__model,resolved,'\\boolean','_$ctxt'(_CallerModuleVar,8,__newcontextvar3)),','('_$_$_flora''rule_enabled'(8,'ctxModel.flr',FLORA_THIS_MODULE_NAME),','(','(FLORA_THIS_WORKSPACE(d^isa)(__model,'ContextClass','_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar9,8)),FLORA_THIS_WORKSPACE(d^mvd)(__model,defBy,__param,'_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar10,8))),fllibexecute_delayed_calls([__model,__param],[__model])))).
:-(FLORA_THIS_WORKSPACE(static^imvdcondef)(__model,resolved,0,*,'_$ctxt'(_CallerModuleVar,8,__newcontextvar4)),','('_$_$_flora''rule_enabled'(8,'ctxModel.flr',FLORA_THIS_MODULE_NAME),','(','(FLORA_THIS_WORKSPACE(d^isa)(__model,'ContextClass','_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar9,8)),FLORA_THIS_WORKSPACE(d^mvd)(__model,defBy,__param,'_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar10,8))),fllibexecute_delayed_calls([__model,__param],[__model])))).
:-(FLORA_THIS_WORKSPACE(static^imvdsig)(__model,specializes,__model,'_$ctxt'(_CallerModuleVar,8,__newcontextvar5)),','('_$_$_flora''rule_enabled'(8,'ctxModel.flr',FLORA_THIS_MODULE_NAME),','(','(FLORA_THIS_WORKSPACE(d^isa)(__model,'ContextClass','_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar9,8)),FLORA_THIS_WORKSPACE(d^mvd)(__model,defBy,__param,'_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar10,8))),fllibexecute_delayed_calls([__model,__param],[__model])))).
:-(FLORA_THIS_WORKSPACE(static^imvdcondef)(__model,specializes,0,*,'_$ctxt'(_CallerModuleVar,8,__newcontextvar6)),','('_$_$_flora''rule_enabled'(8,'ctxModel.flr',FLORA_THIS_MODULE_NAME),','(','(FLORA_THIS_WORKSPACE(d^isa)(__model,'ContextClass','_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar9,8)),FLORA_THIS_WORKSPACE(d^mvd)(__model,defBy,__param,'_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar10,8))),fllibexecute_delayed_calls([__model,__param],[__model])))).
:-(FLORA_THIS_WORKSPACE(static^imvdsig)(__model,__param,__param,'_$ctxt'(_CallerModuleVar,8,__newcontextvar7)),','('_$_$_flora''rule_enabled'(8,'ctxModel.flr',FLORA_THIS_MODULE_NAME),','(FLORA_THIS_WORKSPACE(d^isa)(__model,'ContextClass','_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar9,8)),FLORA_THIS_WORKSPACE(d^mvd)(__model,defBy,__param,'_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar10,8))))).
:-(FLORA_THIS_WORKSPACE(static^imvdcondef)(__model,__param,0,*,'_$ctxt'(_CallerModuleVar,8,__newcontextvar8)),','('_$_$_flora''rule_enabled'(8,'ctxModel.flr',FLORA_THIS_MODULE_NAME),','(FLORA_THIS_WORKSPACE(d^isa)(__model,'ContextClass','_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar9,8)),FLORA_THIS_WORKSPACE(d^mvd)(__model,defBy,__param,'_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar10,8))))).
:-(FLORA_THIS_WORKSPACE(static^imvdsig)(__param,covers,__param,'_$ctxt'(_CallerModuleVar,10,__newcontextvar1)),','('_$_$_flora''rule_enabled'(10,'ctxModel.flr',FLORA_THIS_MODULE_NAME),FLORA_THIS_WORKSPACE(d^isa)(__param,'Parameter','_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar5,10)))).
:-(FLORA_THIS_WORKSPACE(static^imvdcondef)(__param,covers,0,*,'_$ctxt'(_CallerModuleVar,10,__newcontextvar2)),','('_$_$_flora''rule_enabled'(10,'ctxModel.flr',FLORA_THIS_MODULE_NAME),FLORA_THIS_WORKSPACE(d^isa)(__param,'Parameter','_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar5,10)))).
:-(FLORA_THIS_WORKSPACE(static^imvdsig)(__param,valueRelationship,__param,'_$ctxt'(_CallerModuleVar,10,__newcontextvar3)),','('_$_$_flora''rule_enabled'(10,'ctxModel.flr',FLORA_THIS_MODULE_NAME),FLORA_THIS_WORKSPACE(d^isa)(__param,'Parameter','_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar5,10)))).
:-(FLORA_THIS_WORKSPACE(static^imvdcondef)(__param,valueRelationship,0,*,'_$ctxt'(_CallerModuleVar,10,__newcontextvar4)),','('_$_$_flora''rule_enabled'(10,'ctxModel.flr',FLORA_THIS_MODULE_NAME),FLORA_THIS_WORKSPACE(d^isa)(__param,'Parameter','_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar5,10)))).
:-(FLORA_THIS_WORKSPACE(static^imvdsig)(__param,flapply(detParamValue,__domainCase),__param,'_$ctxt'(_CallerModuleVar,12,__newcontextvar1)),','('_$_$_flora''rule_enabled'(12,'ctxModel.flr',FLORA_THIS_MODULE_NAME),','(FLORA_THIS_WORKSPACE(d^isa)(__param,'Parameter','_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar3,12)),FLORA_WORKSPACE(bc,d^isa)(__domainCase,'BusinessCaseClass','_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar4,12))))).
:-(FLORA_THIS_WORKSPACE(static^imvdcondef)(__param,flapply(detParamValue,__domainCase),0,*,'_$ctxt'(_CallerModuleVar,12,__newcontextvar2)),','('_$_$_flora''rule_enabled'(12,'ctxModel.flr',FLORA_THIS_MODULE_NAME),','(FLORA_THIS_WORKSPACE(d^isa)(__param,'Parameter','_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar3,12)),FLORA_WORKSPACE(bc,d^isa)(__domainCase,'BusinessCaseClass','_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar4,12))))).
:-(FLORA_THIS_WORKSPACE(static^isa)(__param,defBy,'_$ctxt'(_CallerModuleVar,14,__newcontextvar1)),','('_$_$_flora''rule_enabled'(14,'ctxModel.flr',FLORA_THIS_MODULE_NAME),FLORA_THIS_WORKSPACE(d^isa)(__param,'Parameter','_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar2,14)))).
:-(FLORA_THIS_WORKSPACE(static^imvdsig)(__model,__param,__param,'_$ctxt'(_CallerModuleVar,16,__newcontextvar1)),','('_$_$_flora''rule_enabled'(16,'ctxModel.flr',FLORA_THIS_MODULE_NAME),','(FLORA_THIS_WORKSPACE(d^isa)(__model,'ContextClass','_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar3,16)),FLORA_THIS_WORKSPACE(d^isa)(__param,'Parameter','_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar4,16))))).
:-(FLORA_THIS_WORKSPACE(static^imvdcondef)(__model,__param,0,*,'_$ctxt'(_CallerModuleVar,16,__newcontextvar2)),','('_$_$_flora''rule_enabled'(16,'ctxModel.flr',FLORA_THIS_MODULE_NAME),','(FLORA_THIS_WORKSPACE(d^isa)(__model,'ContextClass','_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar3,16)),FLORA_THIS_WORKSPACE(d^isa)(__param,'Parameter','_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar4,16))))).
:-(FLORA_THIS_WORKSPACE(static^mvd)(__parent,covers,__ancestor,'_$ctxt'(_CallerModuleVar,18,__newcontextvar1)),','('_$_$_flora''rule_enabled'(18,'ctxModel.flr',FLORA_THIS_MODULE_NAME),','(','(FLORA_THIS_WORKSPACE(d^isa)(___value,'Parameter','_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar2,18)),','(','(FLORA_THIS_WORKSPACE(d^mvd)(__parent,covers,___child,'_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar4,18)),FLORA_THIS_WORKSPACE(d^isa)(__parent,___value,'_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar3,18))),','(','(FLORA_THIS_WORKSPACE(d^mvd)(___child,covers,__ancestor,'_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar7,18)),FLORA_THIS_WORKSPACE(d^isa)(__ancestor,___value,'_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar6,18))),FLORA_THIS_WORKSPACE(d^isa)(___child,___value,'_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar5,18))))),fllibexecute_delayed_calls([___child,___value,__ancestor,__parent],[__ancestor,__parent])))).
:-(FLORA_THIS_WORKSPACE(static^mvd)(__parent,covers,__parent,'_$ctxt'(_CallerModuleVar,20,__newcontextvar1)),','('_$_$_flora''rule_enabled'(20,'ctxModel.flr',FLORA_THIS_MODULE_NAME),','(','(FLORA_THIS_WORKSPACE(d^isa)(__parent,___dim,'_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar2,20)),FLORA_THIS_WORKSPACE(d^isa)(___dim,'Parameter','_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar3,20))),fllibexecute_delayed_calls([___dim,__parent],[__parent])))).
:-(FLORA_THIS_WORKSPACE(static^imvdsig)(__model,flapply(paramCovers,'Parameter'),__model,'_$ctxt'(_CallerModuleVar,22,__newcontextvar1)),','('_$_$_flora''rule_enabled'(22,'ctxModel.flr',FLORA_THIS_MODULE_NAME),FLORA_THIS_WORKSPACE(d^isa)(__model,'ContextClass','_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar3,22)))).
:-(FLORA_THIS_WORKSPACE(static^imvdcondef)(__model,flapply(paramCovers,'Parameter'),0,*,'_$ctxt'(_CallerModuleVar,22,__newcontextvar2)),','('_$_$_flora''rule_enabled'(22,'ctxModel.flr',FLORA_THIS_MODULE_NAME),FLORA_THIS_WORKSPACE(d^isa)(__model,'ContextClass','_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar3,22)))).
:-(FLORA_THIS_WORKSPACE(static^mvd)(__ctx,flapply(paramCovers,__param),__cov,'_$ctxt'(_CallerModuleVar,24,__newcontextvar1)),','('_$_$_flora''rule_enabled'(24,'ctxModel.flr',FLORA_THIS_MODULE_NAME),','(','(FLORA_THIS_WORKSPACE(d^isa)(__model,'ContextClass','_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar2,24)),','(','(FLORA_THIS_WORKSPACE(d^isa)(__ctx,__model,'_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar3,24)),FLORA_THIS_WORKSPACE(d^isa)(__cov,__model,'_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar4,24))),','(','(FLORA_THIS_WORKSPACE(d^isa)(__param,'Parameter','_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar5,24)),FLORA_THIS_WORKSPACE(d^mvd)(__ctx,__param,___v1,'_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar6,24))),','(','(FLORA_THIS_WORKSPACE(d^isa)(__param,'Parameter','_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar7,24)),FLORA_THIS_WORKSPACE(d^mvd)(__cov,__param,___v2,'_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar8,24))),FLORA_THIS_WORKSPACE(d^mvd)(___v1,covers,___v2,'_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar9,24)))))),fllibexecute_delayed_calls([___v1,___v2,__cov,__ctx,__model,__param],[__cov,__ctx,__param])))).
:-(FLORA_THIS_WORKSPACE(static^mvd)(__cov,specialises,__ctx,'_$ctxt'(_CallerModuleVar,26,__newcontextvar1)),','('_$_$_flora''rule_enabled'(26,'ctxModel.flr',FLORA_THIS_MODULE_NAME),','(','(FLORA_THIS_WORKSPACE(d^isa)(__model,'ContextClass','_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar2,26)),','(','(FLORA_THIS_WORKSPACE(d^isa)(__ctx,__model,'_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar3,26)),FLORA_THIS_WORKSPACE(d^isa)(__cov,__model,'_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar4,26))),','(FLORA_THIS_WORKSPACE(d^mvd)(__ctx,flapply(paramCovers,__newdontcarevar5),__cov,'_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar6,26)),fllibtruthvalue(true,FLORA_THIS_MODULE_NAME,flibnafdelay(flora_naf(FLORA_THIS_WORKSPACE(tabled_unnumber_call)(','(','(','(FLORA_THIS_WORKSPACE(d^mvd)(__newdontcarevar7,defBy,__newquantvar11,'_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar9,26)),FLORA_THIS_WORKSPACE(d^isa)(__newdontcarevar7,'ContextClass','_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar8,26))),flibnafdelay(flora_naf(FLORA_THIS_WORKSPACE(tabled_unnumber_call)(','(FLORA_THIS_WORKSPACE(d^mvd)(__ctx,flapply(paramCovers,__newquantvar11),__cov,'_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar10,26)),fllibexecute_delayed_calls([__cov,__ctx,__newquantvar11],[]))),[__ctx,__newquantvar11,__cov],45,'ctxModel.flr'))),fllibexecute_delayed_calls([__cov,__ctx,__newquantvar11],[]))),[__ctx,__cov,__cov,__ctx,__ctx,__cov],45,'ctxModel.flr')))))),fllibexecute_delayed_calls([__newdontcarevar12,__cov,__ctx,__model],[__cov,__ctx])))).
:-(FLORA_THIS_WORKSPACE(static^mvd)(__model,flapply(detRelevantCtxs,__bc),__ctx,'_$ctxt'(_CallerModuleVar,28,__newcontextvar1)),','('_$_$_flora''rule_enabled'(28,'ctxModel.flr',FLORA_THIS_MODULE_NAME),','(','(FLORA_THIS_WORKSPACE(d^isa)(__model,'ContextClass','_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar2,28)),','(FLORA_THIS_WORKSPACE(d^isa)(__ctx,__model,'_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar3,28)),fllibtruthvalue(true,FLORA_THIS_MODULE_NAME,flibnafdelay(flora_naf(FLORA_THIS_WORKSPACE(tabled_unnumber_call)(','(','(','(FLORA_THIS_WORKSPACE(d^isa)(__newdontcarevar4,'Parameter','_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar5,28)),FLORA_THIS_WORKSPACE(d^mvd)(__ctx,__newdontcarevar4,__newquantvar12,'_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar6,28))),flibnafdelay(flora_naf(FLORA_THIS_WORKSPACE(tabled_unnumber_call)(','(','(','(','(FLORA_THIS_WORKSPACE(d^mvd)(___d,flapply(detParamValue,__bc),__val,'_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar9,28)),FLORA_THIS_WORKSPACE(d^isa)(__val,___d,'_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar8,28))),FLORA_THIS_WORKSPACE(d^isa)(___d,'Parameter','_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar7,28))),','(FLORA_THIS_WORKSPACE(d^mvd)(__newquantvar12,covers,__val,'_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar11,28)),FLORA_THIS_WORKSPACE(d^isa)(__newquantvar12,___d,'_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar10,28)))),fllibexecute_delayed_calls([___d,__bc,__newquantvar12,__val],[]))),[__bc,__newquantvar12,__newquantvar12],53,'ctxModel.flr'))),fllibexecute_delayed_calls([___d,__bc,__ctx,__newquantvar12,__val],[]))),[__ctx,___d,__bc,__val,__val,___d,___d,__val,___d,___d,__bc,__val,__bc],53,'ctxModel.flr'))))),fllibexecute_delayed_calls([__newdontcarevar13,___d,__bc,__ctx,__model,__val],[__bc,__ctx,__model])))).
:-(FLORA_THIS_WORKSPACE(static^tran)(__model,flapply(detCleanCaseSpecificCtx,__bc,__mTarget,__ctxf),'_$ctxt'(_CallerModuleVar,30,__newcontextvar1)),','('_$_$_flora''rule_enabled'(30,'ctxModel.flr',FLORA_THIS_MODULE_NAME),','(flliberasemodule(FLORA_THIS_MODULE_NAME,__mTarget),','(!,FLORA_THIS_WORKSPACE(d^tran)(__model,flapply(detCaseSpecificCtx,__bc,__mTarget,__ctxf),'_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar2,30)))))).
:-(FLORA_THIS_WORKSPACE(static^tran)(__model,flapply(detCaseSpecificCtx,__bc,__mTarget,__ctxf),'_$ctxt'(_CallerModuleVar,32,__newcontextvar1)),','('_$_$_flora''rule_enabled'(32,'ctxModel.flr',FLORA_THIS_MODULE_NAME),','(','(FLORA_THIS_WORKSPACE(d^isa)(__model,'ContextClass','_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar2,32)),','(FLORA_THIS_WORKSPACE(d^mvd)(__model,flapply(detRelevantCtxs,__bc),__ctx,'_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar3,32)),','(FLORA_THIS_WORKSPACE(d^mvd)(__ctx,file,__ctxf,'_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar4,32)),fllibhypothetical(<>,fllibload([>>(+(__ctxf),__mTarget)],'C:/TEMP/dke/flora2/CBRM/ctxModelAIM.flr',FLORA_THIS_MODULE_NAME,[65,35]))))),fllibexecute_delayed_calls([__bc,__ctx,__ctxf,__mTarget,__model],[__bc,__ctxf,__mTarget,__model])))).
:-(FLORA_THIS_WORKSPACE(static^tran)(__model,delete,'_$ctxt'(_CallerModuleVar,34,__newcontextvar1)),','('_$_$_flora''rule_enabled'(34,'ctxModel.flr',FLORA_THIS_MODULE_NAME),','(','(FLORA_THIS_WORKSPACE(d^isa)(__model,'ContextClass','_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar2,34)),','(flliberaseall(FLORA_THIS_MODULE_NAME,[flsysdbupdate(FLORA_THIS_WORKSPACE(mvd)(__newdontcarevar3,__newdontcarevar4,__newdontcarevar5,'_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar7,__newcontextvar6)),FLORA_THIS_FDB_STORAGE,FLORA_THIS_MODULE_NAME),flsysdbupdate(FLORA_THIS_WORKSPACE(isa)(__newdontcarevar8,__model,'_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar10,__newcontextvar9)),FLORA_THIS_FDB_STORAGE,FLORA_THIS_MODULE_NAME)]),','(flliberaseall(FLORA_THIS_MODULE_NAME,[flsysdbupdate(FLORA_THIS_WORKSPACE(isa)(__newdontcarevar11,__param,'_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar13,__newcontextvar12)),FLORA_THIS_FDB_STORAGE,FLORA_THIS_MODULE_NAME),flsysdbupdate(FLORA_THIS_WORKSPACE(isa)(__param,'Parameter','_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar15,__newcontextvar14)),FLORA_THIS_FDB_STORAGE,FLORA_THIS_MODULE_NAME)]),flliberaseall(FLORA_THIS_MODULE_NAME,[flsysdbupdate(FLORA_THIS_WORKSPACE(isa)(__model,'ContextClass','_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar17,__newcontextvar16)),FLORA_THIS_FDB_STORAGE,FLORA_THIS_MODULE_NAME)])))),fllibexecute_delayed_calls([__newdontcarevar18,__model,__param],[__model])))).
:-(FLORA_THIS_WORKSPACE(static^tran)(__model,flapply(addParam,__param,__rootVal,__ctx,__detParamVal),'_$ctxt'(_CallerModuleVar,36,__newcontextvar1)),','('_$_$_flora''rule_enabled'(36,'ctxModel.flr',FLORA_THIS_MODULE_NAME),','(FLORA_THIS_WORKSPACE(d^isa)(__model,'ContextClass','_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar2,36)),','(FLORA_THIS_WORKSPACE(d^isa)(__ctx,__model,'_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar3,36)),','(fllibbtinsert(FLORA_THIS_MODULE_NAME,[flsysdbupdate(FLORA_THIS_WORKSPACE(isa)(__param,'Parameter','_$ctxt'(_CallerModuleVar,__newcontextvar4,__newcontextvar5)),FLORA_THIS_FDB_STORAGE,FLORA_THIS_MODULE_NAME)]),','(fllibinsert(FLORA_THIS_MODULE_NAME,[__detParamVal]),fllibbtinsertall(FLORA_THIS_MODULE_NAME,[flsysdbupdate(FLORA_THIS_WORKSPACE(isa)(__rootVal,__param,'_$ctxt'(_CallerModuleVar,__newcontextvar6,__newcontextvar7)),FLORA_THIS_FDB_STORAGE,FLORA_THIS_MODULE_NAME),flsysdbupdate(FLORA_THIS_WORKSPACE(mvd)(__model,defBy,__param,'_$ctxt'(_CallerModuleVar,__newcontextvar8,__newcontextvar9)),FLORA_THIS_FDB_STORAGE,FLORA_THIS_MODULE_NAME),flsysdbupdate(FLORA_THIS_WORKSPACE(mvd)(__ctx,__param,__rootVal,'_$ctxt'(_CallerModuleVar,__newcontextvar10,__newcontextvar11)),FLORA_THIS_FDB_STORAGE,FLORA_THIS_MODULE_NAME)]))))))).
:-(FLORA_THIS_WORKSPACE(static^tran)(__model,flapply(delParam,__param,__id),'_$ctxt'(_CallerModuleVar,38,__newcontextvar1)),','('_$_$_flora''rule_enabled'(38,'ctxModel.flr',FLORA_THIS_MODULE_NAME),','(','(FLORA_THIS_WORKSPACE(d^isa)(__model,'ContextClass','_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar2,38)),','(fllibbtdeleteall(FLORA_THIS_MODULE_NAME,[flsysdbupdate(FLORA_THIS_WORKSPACE(isa)(__param,'Parameter','_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar4,__newcontextvar3)),FLORA_THIS_FDB_STORAGE,FLORA_THIS_MODULE_NAME),flsysdbupdate(FLORA_THIS_WORKSPACE(mvd)(__newdontcarevar5,defBy,__param,'_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar7,__newcontextvar6)),FLORA_THIS_FDB_STORAGE,FLORA_THIS_MODULE_NAME),flsysdbupdate(FLORA_THIS_WORKSPACE(isa)(__param,'Parameter','_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar9,__newcontextvar8)),FLORA_THIS_FDB_STORAGE,FLORA_THIS_MODULE_NAME)]),','(fllibclause(__id,__newvar10,','('_$_$_flora''prop_descriptor'(__id,__newvar11,__newvar12,type,__newvar14,__newvar10),'_$_$_flora''tag_descriptor'(__id,__newvar11,__newvar12,__newvar13,__newvar10)),null,[fllibmodlit('d^mvd',[__param,flapply(detParamValue,__newdontcarevar15),__newdontcarevar16,'_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar17,38)],__newdontcarevar18,'_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar19,38))],__newdontcarevar20),','(:(flrdescriptor_support,disable_rule_by_id(__id,__newdontcarevar21,FLORA_THIS_MODULE_NAME)),','(fllibbtdeleteall(FLORA_THIS_MODULE_NAME,[flsysdbupdate(FLORA_THIS_WORKSPACE(isa)(__newdontcarevar22,__param,'_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar24,__newcontextvar23)),FLORA_THIS_FDB_STORAGE,FLORA_THIS_MODULE_NAME)]),','(fllibbtdeleteall(FLORA_THIS_MODULE_NAME,[flsysdbupdate(FLORA_THIS_WORKSPACE(mvd)(__x,covers,__y,'_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar26,__newcontextvar25)),FLORA_THIS_FDB_STORAGE,FLORA_THIS_MODULE_NAME),flsysdbupdate(FLORA_THIS_WORKSPACE(isa)(__x,__param,'_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar28,__newcontextvar27)),FLORA_THIS_FDB_STORAGE,FLORA_THIS_MODULE_NAME),flsysdbupdate(FLORA_THIS_WORKSPACE(isa)(__y,__param,'_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar30,__newcontextvar29)),FLORA_THIS_FDB_STORAGE,FLORA_THIS_MODULE_NAME)]),fllibbtdeleteall(FLORA_THIS_MODULE_NAME,[flsysdbupdate(FLORA_THIS_WORKSPACE(mvd)(__newdontcarevar31,__param,__newdontcarevar32,'_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar34,__newcontextvar33)),FLORA_THIS_FDB_STORAGE,FLORA_THIS_MODULE_NAME)]))))))),fllibexecute_delayed_calls([__newdontcarevar35,__id,__model,__param,__x,__y],[__id,__model,__param])))).
:-(FLORA_THIS_WORKSPACE(static^tran)(__model,flapply(addValueLeaf,__parent,__newVal),'_$ctxt'(_CallerModuleVar,40,__newcontextvar1)),','('_$_$_flora''rule_enabled'(40,'ctxModel.flr',FLORA_THIS_MODULE_NAME),','(','(FLORA_THIS_WORKSPACE(d^isa)(__model,'ContextClass','_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar2,40)),','(FLORA_THIS_WORKSPACE(d^isa)(__parent,__param,'_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar3,40)),','(FLORA_THIS_WORKSPACE(d^isa)(__param,'Parameter','_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar4,40)),','(fllibbtinsert(FLORA_THIS_MODULE_NAME,[flsysdbupdate(FLORA_THIS_WORKSPACE(isa)(__newVal,__param,'_$ctxt'(_CallerModuleVar,__newcontextvar5,__newcontextvar6)),FLORA_THIS_FDB_STORAGE,FLORA_THIS_MODULE_NAME)]),fllibbtinsert(FLORA_THIS_MODULE_NAME,[flsysdbupdate(FLORA_THIS_WORKSPACE(mvd)(__parent,covers,__newVal,'_$ctxt'(_CallerModuleVar,__newcontextvar7,__newcontextvar8)),FLORA_THIS_FDB_STORAGE,FLORA_THIS_MODULE_NAME)]))))),fllibexecute_delayed_calls([__model,__newVal,__param,__parent],[__model,__newVal,__parent])))).
:-(FLORA_THIS_WORKSPACE(static^tran)(__model,flapply(addValueNode,__parent,__newVal,__child),'_$ctxt'(_CallerModuleVar,42,__newcontextvar1)),','('_$_$_flora''rule_enabled'(42,'ctxModel.flr',FLORA_THIS_MODULE_NAME),','(','(FLORA_THIS_WORKSPACE(d^isa)(__model,'ContextClass','_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar2,42)),','(FLORA_THIS_WORKSPACE(d^isa)(__parent,__param,'_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar3,42)),','(FLORA_THIS_WORKSPACE(d^isa)(__param,'Parameter','_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar4,42)),','(fllibbtinsert(FLORA_THIS_MODULE_NAME,[flsysdbupdate(FLORA_THIS_WORKSPACE(isa)(__newVal,__param,'_$ctxt'(_CallerModuleVar,__newcontextvar5,__newcontextvar6)),FLORA_THIS_FDB_STORAGE,FLORA_THIS_MODULE_NAME)]),','(fllibbtinsert(FLORA_THIS_MODULE_NAME,[flsysdbupdate(FLORA_THIS_WORKSPACE(mvd)(__parent,covers,__newVal,'_$ctxt'(_CallerModuleVar,__newcontextvar7,__newcontextvar8)),FLORA_THIS_FDB_STORAGE,FLORA_THIS_MODULE_NAME),flsysdbupdate(FLORA_THIS_WORKSPACE(mvd)(__newVal,covers,__child,'_$ctxt'(_CallerModuleVar,__newcontextvar9,__newcontextvar10)),FLORA_THIS_FDB_STORAGE,FLORA_THIS_MODULE_NAME)]),fllibbtdeleteall(FLORA_THIS_MODULE_NAME,[flsysdbupdate(FLORA_THIS_WORKSPACE(mvd)(__parent,covers,__child,'_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar12,__newcontextvar11)),FLORA_THIS_FDB_STORAGE,FLORA_THIS_MODULE_NAME)])))))),fllibexecute_delayed_calls([__child,__model,__newVal,__param,__parent],[__child,__model,__newVal,__parent])))).
:-(FLORA_THIS_WORKSPACE(static^tran)(__model,flapply(addValueRoot,__newVal,__child),'_$ctxt'(_CallerModuleVar,44,__newcontextvar1)),','('_$_$_flora''rule_enabled'(44,'ctxModel.flr',FLORA_THIS_MODULE_NAME),','(','(FLORA_THIS_WORKSPACE(d^isa)(__model,'ContextClass','_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar2,44)),','(FLORA_THIS_WORKSPACE(d^isa)(__child,__param,'_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar3,44)),','(FLORA_THIS_WORKSPACE(d^isa)(__param,'Parameter','_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar4,44)),','(fllibbtinsert(FLORA_THIS_MODULE_NAME,[flsysdbupdate(FLORA_THIS_WORKSPACE(isa)(__newVal,__param,'_$ctxt'(_CallerModuleVar,__newcontextvar5,__newcontextvar6)),FLORA_THIS_FDB_STORAGE,FLORA_THIS_MODULE_NAME)]),fllibbtinsert(FLORA_THIS_MODULE_NAME,[flsysdbupdate(FLORA_THIS_WORKSPACE(mvd)(__newVal,covers,__child,'_$ctxt'(_CallerModuleVar,__newcontextvar7,__newcontextvar8)),FLORA_THIS_FDB_STORAGE,FLORA_THIS_MODULE_NAME)]))))),fllibexecute_delayed_calls([__child,__model,__newVal,__param],[__child,__model,__newVal])))).
:-(FLORA_THIS_WORKSPACE(static^tran)(__model,flapply(delValueSubGraph,__val),'_$ctxt'(_CallerModuleVar,46,__newcontextvar1)),','('_$_$_flora''rule_enabled'(46,'ctxModel.flr',FLORA_THIS_MODULE_NAME),','(','(FLORA_THIS_WORKSPACE(d^isa)(__model,'ContextClass','_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar2,46)),','(fllibbtdeleteall(FLORA_THIS_MODULE_NAME,[flsysdbupdate(FLORA_THIS_WORKSPACE(mvd)(__newdontcarevar3,covers,__val,'_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar5,__newcontextvar4)),FLORA_THIS_FDB_STORAGE,FLORA_THIS_MODULE_NAME)]),flliberase(FLORA_THIS_MODULE_NAME,[flsysdbupdate(FLORA_THIS_WORKSPACE(isa)(__val,__newdontcarevar6,'_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar8,__newcontextvar7)),FLORA_THIS_FDB_STORAGE,FLORA_THIS_MODULE_NAME)]))),fllibexecute_delayed_calls([__newdontcarevar9,__model,__val],[__model,__val])))).
:-(FLORA_THIS_WORKSPACE(static^tran)(__model,flapply(delValue,__val,__parent),'_$ctxt'(_CallerModuleVar,48,__newcontextvar1)),','('_$_$_flora''rule_enabled'(48,'ctxModel.flr',FLORA_THIS_MODULE_NAME),','(','(FLORA_THIS_WORKSPACE(d^isa)(__model,'ContextClass','_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar2,48)),','(FLORA_THIS_WORKSPACE(d^mvd)(__parent,covers,__val,'_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar3,48)),','(flibnafdelay(flora_naf(FLORA_THIS_WORKSPACE(tabled_unnumber_call)(','(=(__parent,__val),fllibexecute_delayed_calls([__parent,__val],[]))),[__parent,__val],131,'ctxModel.flr')),','(FLORA_THIS_WORKSPACE(d^mvd)(__val,covers,__children,'_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar4,48)),','(flibnafdelay(flora_naf(FLORA_THIS_WORKSPACE(tabled_unnumber_call)(','(=(__val,__children),fllibexecute_delayed_calls([__children,__val],[]))),[__val,__children],133,'ctxModel.flr')),','(fllibbtdeleteall(FLORA_THIS_MODULE_NAME,[flsysdbupdate(FLORA_THIS_WORKSPACE(mvd)(__newdontcarevar5,covers,__val,'_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar7,__newcontextvar6)),FLORA_THIS_FDB_STORAGE,FLORA_THIS_MODULE_NAME)]),','(fllibbtdeleteall(FLORA_THIS_MODULE_NAME,[flsysdbupdate(FLORA_THIS_WORKSPACE(mvd)(__val,covers,__newdontcarevar8,'_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar10,__newcontextvar9)),FLORA_THIS_FDB_STORAGE,FLORA_THIS_MODULE_NAME)]),','(fllibbtdeleteall(FLORA_THIS_MODULE_NAME,[flsysdbupdate(FLORA_THIS_WORKSPACE(isa)(__val,__newdontcarevar11,'_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar13,__newcontextvar12)),FLORA_THIS_FDB_STORAGE,FLORA_THIS_MODULE_NAME)]),fllibbtinsertall(FLORA_THIS_MODULE_NAME,[flsysdbupdate(FLORA_THIS_WORKSPACE(mvd)(__parent,covers,__children,'_$ctxt'(_CallerModuleVar,__newcontextvar14,__newcontextvar15)),FLORA_THIS_FDB_STORAGE,FLORA_THIS_MODULE_NAME)]))))))))),fllibexecute_delayed_calls([__newdontcarevar16,__children,__model,__parent,__val],[__model,__parent,__val])))).
:-(FLORA_THIS_WORKSPACE(static^tran)(__model,delCtxByValue,'_$ctxt'(_CallerModuleVar,50,__newcontextvar1)),','('_$_$_flora''rule_enabled'(50,'ctxModel.flr',FLORA_THIS_MODULE_NAME),','(','(FLORA_THIS_WORKSPACE(d^isa)(__model,'ContextClass','_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar2,50)),fllibbtdeleteall(FLORA_THIS_MODULE_NAME,[flsysdbupdate(FLORA_THIS_WORKSPACE(mvd)(__ctx,__newdontcarevar3,__newdontcarevar4,'_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar6,__newcontextvar5)),FLORA_THIS_FDB_STORAGE,FLORA_THIS_MODULE_NAME),flsysdbupdate(FLORA_THIS_WORKSPACE(isa)(__ctx,__model,'_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar8,__newcontextvar7)),FLORA_THIS_FDB_STORAGE,FLORA_THIS_MODULE_NAME)],','(','(FLORA_THIS_WORKSPACE(d^mvd)(__ctx,__param,__val,'_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar10,50)),FLORA_THIS_WORKSPACE(d^isa)(__ctx,__model,'_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar9,50))),','(FLORA_THIS_WORKSPACE(d^isa)(__param,'Parameter','_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar11,50)),flibnafdelay(flora_naf(FLORA_THIS_WORKSPACE(tabled_unnumber_call)(','(FLORA_THIS_WORKSPACE(d^isa)(__val,__param,'_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar12,50)),fllibexecute_delayed_calls([__param,__val],[]))),[__val,__param],142,'ctxModel.flr')))))),fllibexecute_delayed_calls([__newdontcarevar13,__ctx,__model,__param,__val],[__model])))).
:-(FLORA_THIS_WORKSPACE(static^tran)(__model,flapply(modCtxByValue,__old,__newVal,__ctx),'_$ctxt'(_CallerModuleVar,52,__newcontextvar1)),','('_$_$_flora''rule_enabled'(52,'ctxModel.flr',FLORA_THIS_MODULE_NAME),','(','(FLORA_THIS_WORKSPACE(d^isa)(__model,'ContextClass','_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar2,52)),','(','(FLORA_THIS_WORKSPACE(d^mvd)(__ctx,__param,__old,'_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar4,52)),FLORA_THIS_WORKSPACE(d^isa)(__ctx,__model,'_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar3,52))),','(FLORA_THIS_WORKSPACE(d^isa)(__newVal,__param,'_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar5,52)),','(fllibbtinsertall(FLORA_THIS_MODULE_NAME,[flsysdbupdate(FLORA_THIS_WORKSPACE(mvd)(__ctx,__param,__newVal,'_$ctxt'(_CallerModuleVar,__newcontextvar6,__newcontextvar7)),FLORA_THIS_FDB_STORAGE,FLORA_THIS_MODULE_NAME)]),fllibbtdeleteall(FLORA_THIS_MODULE_NAME,[flsysdbupdate(FLORA_THIS_WORKSPACE(mvd)(__ctx,__newdontcarevar8,__old,'_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar10,__newcontextvar9)),FLORA_THIS_FDB_STORAGE,FLORA_THIS_MODULE_NAME)]))))),fllibexecute_delayed_calls([__newdontcarevar11,__ctx,__model,__newVal,__old,__param],[__ctx,__model,__newVal,__old])))).
:-(FLORA_THIS_WORKSPACE(static^tran)(__ctx,flapply(situationalModification,__ctxTarget,__param),'_$ctxt'(_CallerModuleVar,54,__newcontextvar1)),','('_$_$_flora''rule_enabled'(54,'ctxModel.flr',FLORA_THIS_MODULE_NAME),','(','(FLORA_THIS_WORKSPACE(d^isa)(__ctx,'AIMCtx','_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar2,54)),','(FLORA_THIS_WORKSPACE(d^isa)(__ctxTarget,'AIMCtx','_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar3,54)),','(fllibbtdeleteall(FLORA_THIS_MODULE_NAME,[flsysdbupdate(FLORA_THIS_WORKSPACE(mvd)(__ctx,__newdontcarevar4,__newdontcarevar5,'_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar7,__newcontextvar6)),FLORA_THIS_FDB_STORAGE,FLORA_THIS_MODULE_NAME)]),','(','(FLORA_THIS_WORKSPACE(d^isa)(__param,'Parameter','_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar8,54)),FLORA_THIS_WORKSPACE(d^mvd)(__ctxTarget,__param,__new,'_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar9,54))),fllibbtinsertall(FLORA_THIS_MODULE_NAME,[flsysdbupdate(FLORA_THIS_WORKSPACE(mvd)(__ctx,__param,__new,'_$ctxt'(_CallerModuleVar,__newcontextvar10,__newcontextvar11)),FLORA_THIS_FDB_STORAGE,FLORA_THIS_MODULE_NAME)]))))),fllibexecute_delayed_calls([__newdontcarevar12,__ctx,__ctxTarget,__new,__param],[__ctx,__ctxTarget,__param])))).
:-(FLORA_THIS_WORKSPACE(static^tran)(__model,flapply(addCtx,__ctx),'_$ctxt'(_CallerModuleVar,56,__newcontextvar1)),','('_$_$_flora''rule_enabled'(56,'ctxModel.flr',FLORA_THIS_MODULE_NAME),','(FLORA_THIS_WORKSPACE(d^isa)(__model,'ContextClass','_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar2,56)),fllibbtinsert(FLORA_THIS_MODULE_NAME,[__ctx])))).
:-(FLORA_THIS_WORKSPACE(static^tran)(__model,flapply(delCtx,__ctx),'_$ctxt'(_CallerModuleVar,58,__newcontextvar1)),','('_$_$_flora''rule_enabled'(58,'ctxModel.flr',FLORA_THIS_MODULE_NAME),','(','(FLORA_THIS_WORKSPACE(d^isa)(__model,'ContextClass','_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar2,58)),','(fllibbtdeleteall(FLORA_THIS_MODULE_NAME,[flsysdbupdate(FLORA_THIS_WORKSPACE(mvd)(__ctx,__newdontcarevar3,__newdontcarevar4,'_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar6,__newcontextvar5)),FLORA_THIS_FDB_STORAGE,FLORA_THIS_MODULE_NAME)],','(FLORA_THIS_WORKSPACE(d^isa)(__ctx,___x,'_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar7,58)),FLORA_THIS_WORKSPACE(d^isa)(___x,'ContextClass','_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar8,58)))),fllibbtdeleteall(FLORA_THIS_MODULE_NAME,[flsysdbupdate(FLORA_THIS_WORKSPACE(isa)(__ctx,__model,'_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar10,__newcontextvar9)),FLORA_THIS_FDB_STORAGE,FLORA_THIS_MODULE_NAME)]))),fllibexecute_delayed_calls([__newdontcarevar11,___x,__ctx,__model],[__ctx,__model])))).
:-(FLORA_THIS_WORKSPACE(static^mvd)(__model,detUnusedValues,__v,'_$ctxt'(_CallerModuleVar,60,__newcontextvar1)),','('_$_$_flora''rule_enabled'(60,'ctxModel.flr',FLORA_THIS_MODULE_NAME),','(','(FLORA_THIS_WORKSPACE(d^isa)(__model,'ContextClass','_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar2,60)),','(FLORA_THIS_WORKSPACE(d^isa)(__v,___param,'_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar3,60)),','(FLORA_THIS_WORKSPACE(d^isa)(___param,'Parameter','_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar4,60)),';'(flibnafdelay(flora_naf(FLORA_THIS_WORKSPACE(tabled_unnumber_call)(','(FLORA_THIS_WORKSPACE(d^mvd)(__newdontcarevar5,___param,__v,'_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar7,60)),fllibexecute_delayed_calls([___param,__v],[]))),[___param,__v,__model],175,'ctxModel.flr')),flibnafdelay(flora_naf(FLORA_THIS_WORKSPACE(tabled_unnumber_call)(','(FLORA_THIS_WORKSPACE(d^isa)(__newdontcarevar5,__model,'_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar6,60)),fllibexecute_delayed_calls([__model],[]))),[___param,__v,__model],175,'ctxModel.flr')))))),fllibexecute_delayed_calls([__newdontcarevar8,___param,__model,__v],[__model,__v])))).
:-(FLORA_THIS_WORKSPACE(static^tran)(__ctx,flapply(addTermRule,__rt),'_$ctxt'(_CallerModuleVar,62,__newcontextvar1)),','('_$_$_flora''rule_enabled'(62,'ctxModel.flr',FLORA_THIS_MODULE_NAME),','(','(FLORA_THIS_WORKSPACE(d^isa)(__ctx,__model,'_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar2,62)),','(FLORA_THIS_WORKSPACE(d^isa)(__model,'ContextClass','_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar3,62)),','(FLORA_THIS_WORKSPACE(d^mvd)(__ctx,file,__ctxf,'_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar4,62)),','(FLORA_WORKSPACE(\\io,d^meth)(__ctxf,flapply(open,append,__Stream),'_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar5,62)),','(FLORA_WORKSPACE(\\io,d^meth)(__Stream,flapply(write,__rt),'_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar6,62)),FLORA_WORKSPACE(\\io,d^meth)(__Stream,close,'_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar7,62))))))),fllibexecute_delayed_calls([__Stream,__ctx,__ctxf,__model,__rt],[__ctx,__rt])))).
:-(FLORA_THIS_WORKSPACE(static^tran)(__ctx,flapply(delTermRule,__rt),'_$ctxt'(_CallerModuleVar,64,__newcontextvar1)),','('_$_$_flora''rule_enabled'(64,'ctxModel.flr',FLORA_THIS_MODULE_NAME),','(','(FLORA_THIS_WORKSPACE(d^isa)(__ctx,__model,'_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar2,64)),','(FLORA_THIS_WORKSPACE(d^isa)(__model,'ContextClass','_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar3,64)),','(FLORA_THIS_WORKSPACE(d^mvd)(__ctx,file,__ctxf,'_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar4,64)),','(FLORA_WORKSPACE(\\io,d^meth)(__ctxf,flapply(open,read,__Stream),'_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar5,64)),','(FLORA_WORKSPACE(\\io,d^meth)('CCtx/Contexts/tmp.tmp',flapply(open,write,__s),'_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar6,64)),','(fllibwhiledo(FLORA_THIS_MODULE_NAME,'\\repeat'(FLORA_WORKSPACE(\\io,d^meth)(__Stream,flapply(readline,atom,__Line),'_$ctxt'(__newcontextvar7,__newcontextvar9,__newcontextvar8))),fllibifthenelse(FLORA_THIS_MODULE_NAME,=(__rt,__Line),FLORA_WORKSPACE(\\io,d^meth)(__Stream,flapply(readline,atom,__newdontcarevar10),'_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar11,64)),FLORA_WORKSPACE(\\io,d^meth)(__s,flapply(write,__Line),'_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar12,64)),191,'ctxModel.flr'),190,'ctxModel.flr'),','(FLORA_WORKSPACE(\\io,d^meth)(__Stream,close,'_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar13,64)),','(FLORA_WORKSPACE(\\io,d^meth)(__s,close,'_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar14,64)),','(FLORA_WORKSPACE(\\io,d^meth)('File',flapply(remove,__ctxf),'_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar15,64)),FLORA_WORKSPACE(\\io,d^meth)('File',flapply(rename,'CCtx/Contexts/tmp.tmp',__ctxf),'_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar16,64))))))))))),fllibexecute_delayed_calls([__Line,__Stream,__newdontcarevar17,__ctx,__ctxf,__model,__rt,__s],[__ctx,__rt])))).
:-(FLORA_THIS_WORKSPACE(static^mvd)('Interest',flapply(detParamValue,__bc),__val,'_$ctxt'(_CallerModuleVar,66,__newcontextvar1)),','('_$_$_flora''rule_enabled'(66,'ctxModelAIM.flr',FLORA_THIS_MODULE_NAME),','(','(FLORA_THIS_WORKSPACE(d^isa)(__val,'Interest','_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar2,66)),','(','(','(FLORA_WORKSPACE(bc,d^mvd)(__bc,interestSpec,__newdontcarevar4,'_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar6,66)),FLORA_WORKSPACE(bc,d^mvd)(__newdontcarevar4,interest,__aircraft,'_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar5,66))),FLORA_WORKSPACE(bc,d^isa)(__bc,'SemNOTAMCase','_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar3,66))),FLORA_THIS_WORKSPACE(d^tblflapply)(getAircraftType,__aircraft,__val,'_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar7,66)))),fllibexecute_delayed_calls([__newdontcarevar8,__aircraft,__bc,__val],[__bc,__val])))).
:-(FLORA_THIS_WORKSPACE(static^mvd)('FlightPhase',flapply(detParamValue,__bc),__val,'_$ctxt'(_CallerModuleVar,68,__newcontextvar1)),','('_$_$_flora''rule_enabled'(68,'ctxModelAIM.flr',FLORA_THIS_MODULE_NAME),','(','(FLORA_THIS_WORKSPACE(d^isa)(__val,'FlightPhase','_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar2,68)),','(','(FLORA_WORKSPACE(bc,d^mvd)(__bc,interestSpec,__newdontcarevar4,'_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar6,68)),FLORA_WORKSPACE(bc,d^mvd)(__newdontcarevar4,flightPhase,__val,'_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar5,68))),FLORA_WORKSPACE(bc,d^isa)(__bc,'SemNOTAMCase','_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar3,68)))),fllibexecute_delayed_calls([__newdontcarevar7,__bc,__val],[__bc,__val])))).
:-(FLORA_THIS_WORKSPACE(static^mvd)('EventScenario',flapply(detParamValue,__bc),__val,'_$ctxt'(_CallerModuleVar,70,__newcontextvar1)),','('_$_$_flora''rule_enabled'(70,'ctxModelAIM.flr',FLORA_THIS_MODULE_NAME),','(','(FLORA_THIS_WORKSPACE(d^isa)(__val,'EventScenario','_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar2,70)),','(','(','(FLORA_WORKSPACE(bc,d^mvd)(__bc,notam,__n,'_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar5,70)),FLORA_WORKSPACE(bc,d^isa)(__n,'NOTAM','_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar4,70))),FLORA_WORKSPACE(bc,d^isa)(__bc,'SemNOTAMCase','_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar3,70))),','(','(FLORA_WORKSPACE(bc,d^mvd)(__n,feature,runway,'_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar6,70)),FLORA_WORKSPACE(bc,d^mvd)(__n,status,closed,'_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar7,70))),=(__val,runwayClosure)))),fllibexecute_delayed_calls([__bc,__n,__val],[__bc,__val])))).
:-(FLORA_THIS_WORKSPACE(static^mvd)('EventScenario',flapply(detParamValue,__bc),__val,'_$ctxt'(_CallerModuleVar,72,__newcontextvar1)),','('_$_$_flora''rule_enabled'(72,'ctxModelAIM.flr',FLORA_THIS_MODULE_NAME),','(','(FLORA_THIS_WORKSPACE(d^isa)(__val,'EventScenario','_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar2,72)),','(','(','(FLORA_WORKSPACE(bc,d^mvd)(__bc,notam,__n,'_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar5,72)),FLORA_WORKSPACE(bc,d^isa)(__n,'NOTAM','_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar4,72))),FLORA_WORKSPACE(bc,d^isa)(__bc,'SemNOTAMCase','_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar3,72))),','(','(FLORA_WORKSPACE(bc,d^mvd)(__n,feature,runway,'_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar6,72)),FLORA_WORKSPACE(bc,d^mvd)(__n,status,limited,'_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar7,72))),=(__val,runwayClosure)))),fllibexecute_delayed_calls([__bc,__n,__val],[__bc,__val])))).
:-(FLORA_THIS_WORKSPACE(static^mvd)('EventScenario',flapply(detParamValue,__bc),__val,'_$ctxt'(_CallerModuleVar,74,__newcontextvar1)),','('_$_$_flora''rule_enabled'(74,'ctxModelAIM.flr',FLORA_THIS_MODULE_NAME),','(','(FLORA_THIS_WORKSPACE(d^isa)(__val,'EventScenario','_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar2,74)),','(','(','(FLORA_WORKSPACE(bc,d^mvd)(__bc,notam,__n,'_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar5,74)),FLORA_WORKSPACE(bc,d^isa)(__n,'NOTAM','_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar4,74))),FLORA_WORKSPACE(bc,d^isa)(__bc,'SemNOTAMCase','_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar3,74))),','(','(FLORA_WORKSPACE(bc,d^mvd)(__n,feature,apron,'_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar6,74)),FLORA_WORKSPACE(bc,d^mvd)(__n,status,closed,'_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar7,74))),=(__val,apronClosure)))),fllibexecute_delayed_calls([__bc,__n,__val],[__bc,__val])))).
:-(FLORA_THIS_WORKSPACE(static^mvd)('EventScenario',flapply(detParamValue,__bc),__val,'_$ctxt'(_CallerModuleVar,76,__newcontextvar1)),','('_$_$_flora''rule_enabled'(76,'ctxModelAIM.flr',FLORA_THIS_MODULE_NAME),','(','(FLORA_THIS_WORKSPACE(d^isa)(__val,'EventScenario','_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar2,76)),','(','(','(FLORA_WORKSPACE(bc,d^mvd)(__bc,notam,__n,'_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar5,76)),FLORA_WORKSPACE(bc,d^isa)(__n,'NOTAM','_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar4,76))),FLORA_WORKSPACE(bc,d^isa)(__bc,'SemNOTAMCase','_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar3,76))),','(FLORA_WORKSPACE(bc,d^mvd)(__n,feature,obstruction,'_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar6,76)),=(__val,obstruction)))),fllibexecute_delayed_calls([__bc,__n,__val],[__bc,__val])))).
:-(FLORA_THIS_WORKSPACE(static^mvd)('EventScenario',flapply(detParamValue,__bc),__val,'_$ctxt'(_CallerModuleVar,78,__newcontextvar1)),','('_$_$_flora''rule_enabled'(78,'ctxModelAIM.flr',FLORA_THIS_MODULE_NAME),','(','(FLORA_THIS_WORKSPACE(d^isa)(__val,'EventScenario','_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar2,78)),','(','(','(FLORA_WORKSPACE(bc,d^mvd)(__bc,notam,__n,'_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar5,78)),FLORA_WORKSPACE(bc,d^isa)(__n,'NOTAM','_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar4,78))),FLORA_WORKSPACE(bc,d^isa)(__bc,'SemNOTAMCase','_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar3,78))),','(FLORA_WORKSPACE(bc,d^mvd)(__n,feature,heliport,'_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar6,78)),=(__val,heliport)))),fllibexecute_delayed_calls([__bc,__n,__val],[__bc,__val])))).
:-(FLORA_THIS_WORKSPACE(static^mvd)('EventScenario',flapply(detParamValue,__bc),__val,'_$ctxt'(_CallerModuleVar,80,__newcontextvar1)),','('_$_$_flora''rule_enabled'(80,'ctxModelAIM.flr',FLORA_THIS_MODULE_NAME),','(','(FLORA_THIS_WORKSPACE(d^isa)(__val,'EventScenario','_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar2,80)),','(','(','(FLORA_WORKSPACE(bc,d^mvd)(__bc,notam,__n,'_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar5,80)),FLORA_WORKSPACE(bc,d^isa)(__n,'NOTAM','_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar4,80))),FLORA_WORKSPACE(bc,d^isa)(__bc,'SemNOTAMCase','_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar3,80))),','(FLORA_WORKSPACE(bc,d^mvd)(__n,feature,waterlaneSki,'_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar6,80)),=(__val,waterlaneSki)))),fllibexecute_delayed_calls([__bc,__n,__val],[__bc,__val])))).
:-(FLORA_THIS_WORKSPACE(static^mvd)('EventScenario',flapply(detParamValue,__bc),__val,'_$ctxt'(_CallerModuleVar,82,__newcontextvar1)),','('_$_$_flora''rule_enabled'(82,'ctxModelAIM.flr',FLORA_THIS_MODULE_NAME),','(','(FLORA_THIS_WORKSPACE(d^isa)(__val,'EventScenario','_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar2,82)),','(','(','(FLORA_WORKSPACE(bc,d^mvd)(__bc,notam,__n,'_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar5,82)),FLORA_WORKSPACE(bc,d^isa)(__n,'NOTAM','_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar4,82))),FLORA_WORKSPACE(bc,d^isa)(__bc,'SemNOTAMCase','_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar3,82))),','(FLORA_WORKSPACE(bc,d^mvd)(__n,feature,aerodromeBeacon,'_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar6,82)),=(__val,aerodromeBeaconStatus)))),fllibexecute_delayed_calls([__bc,__n,__val],[__bc,__val])))).
:-(FLORA_THIS_WORKSPACE(static^mvd)('EventScenario',flapply(detParamValue,__bc),__val,'_$ctxt'(_CallerModuleVar,84,__newcontextvar1)),','('_$_$_flora''rule_enabled'(84,'ctxModelAIM.flr',FLORA_THIS_MODULE_NAME),','(','(FLORA_THIS_WORKSPACE(d^isa)(__val,'EventScenario','_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar2,84)),','(','(','(FLORA_WORKSPACE(bc,d^mvd)(__bc,notam,__n,'_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar5,84)),FLORA_WORKSPACE(bc,d^isa)(__n,'NOTAM','_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar4,84))),FLORA_WORKSPACE(bc,d^isa)(__bc,'SemNOTAMCase','_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar3,84))),','(FLORA_WORKSPACE(bc,d^mvd)(__n,feature,aerodromeILS,'_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar6,84)),=(__val,aerodromeEquipment)))),fllibexecute_delayed_calls([__bc,__n,__val],[__bc,__val])))).

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% Rule signatures %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

?-(fllibinsrulesig(4,'ctxModel.flr','_$_$_flora''descr_vars',FLORA_THIS_MODULE_NAME,6,FLORA_THIS_WORKSPACE(d^mvdsig)(__model,flapply(detRelevantCtxs,__domainCase),__model,'_$ctxt'(_CallerModuleVar,4,__newcontextvar1)),','(FLORA_THIS_WORKSPACE(d^isa)(__model,'ContextClass','_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar3,4)),FLORA_WORKSPACE(bc,d^isa)(__domainCase,'BusinessCaseClass','_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar4,4))),null,'_$_$_flora''rule_enabled'(4,'ctxModel.flr',FLORA_THIS_MODULE_NAME),null,true)).
?-(fllibinsrulesig(4,'ctxModel.flr','_$_$_flora''descr_vars',FLORA_THIS_MODULE_NAME,6,FLORA_THIS_WORKSPACE(mvdcondef)(__model,flapply(detRelevantCtxs,__domainCase),0,*,'_$ctxt'(_CallerModuleVar,4,__newcontextvar2)),','(FLORA_THIS_WORKSPACE(d^isa)(__model,'ContextClass','_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar3,4)),FLORA_WORKSPACE(bc,d^isa)(__domainCase,'BusinessCaseClass','_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar4,4))),null,'_$_$_flora''rule_enabled'(4,'ctxModel.flr',FLORA_THIS_MODULE_NAME),null,true)).
?-(fllibinsrulesig(6,'ctxModel.flr','_$_$_flora''descr_vars',FLORA_THIS_MODULE_NAME,7,FLORA_THIS_WORKSPACE(d^mvdsig)(__model,flapply(detCaseSpecificCtx,__domainCase,__newdontcarevar1,__newdontcarevar2),__model,'_$ctxt'(_CallerModuleVar,6,__newcontextvar3)),','(FLORA_THIS_WORKSPACE(d^isa)(__model,'ContextClass','_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar5,6)),FLORA_WORKSPACE(bc,d^isa)(__domainCase,'BusinessCaseClass','_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar6,6))),null,'_$_$_flora''rule_enabled'(6,'ctxModel.flr',FLORA_THIS_MODULE_NAME),fllibexecute_delayed_calls([__domainCase,__model],[__newdontcarevar7,__domainCase,__model]),true)).
?-(fllibinsrulesig(6,'ctxModel.flr','_$_$_flora''descr_vars',FLORA_THIS_MODULE_NAME,7,FLORA_THIS_WORKSPACE(mvdcondef)(__model,flapply(detCaseSpecificCtx,__domainCase,__newdontcarevar1,__newdontcarevar2),0,*,'_$ctxt'(_CallerModuleVar,6,__newcontextvar4)),','(FLORA_THIS_WORKSPACE(d^isa)(__model,'ContextClass','_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar5,6)),FLORA_WORKSPACE(bc,d^isa)(__domainCase,'BusinessCaseClass','_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar6,6))),null,'_$_$_flora''rule_enabled'(6,'ctxModel.flr',FLORA_THIS_MODULE_NAME),fllibexecute_delayed_calls([__domainCase,__model],[__newdontcarevar7,__domainCase,__model]),true)).
?-(fllibinsrulesig(8,'ctxModel.flr','_$_$_flora''descr_vars',FLORA_THIS_MODULE_NAME,8,FLORA_THIS_WORKSPACE(d^imvdsig)(__model,file,'\\string','_$ctxt'(_CallerModuleVar,8,__newcontextvar1)),','(FLORA_THIS_WORKSPACE(d^isa)(__model,'ContextClass','_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar9,8)),FLORA_THIS_WORKSPACE(d^mvd)(__model,defBy,__param,'_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar10,8))),null,'_$_$_flora''rule_enabled'(8,'ctxModel.flr',FLORA_THIS_MODULE_NAME),fllibexecute_delayed_calls([__model,__param],[__model]),true)).
?-(fllibinsrulesig(8,'ctxModel.flr','_$_$_flora''descr_vars',FLORA_THIS_MODULE_NAME,8,FLORA_THIS_WORKSPACE(imvdcondef)(__model,file,0,1,'_$ctxt'(_CallerModuleVar,8,__newcontextvar2)),','(FLORA_THIS_WORKSPACE(d^isa)(__model,'ContextClass','_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar9,8)),FLORA_THIS_WORKSPACE(d^mvd)(__model,defBy,__param,'_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar10,8))),null,'_$_$_flora''rule_enabled'(8,'ctxModel.flr',FLORA_THIS_MODULE_NAME),fllibexecute_delayed_calls([__model,__param],[__model]),true)).
?-(fllibinsrulesig(8,'ctxModel.flr','_$_$_flora''descr_vars',FLORA_THIS_MODULE_NAME,8,FLORA_THIS_WORKSPACE(d^imvdsig)(__model,resolved,'\\boolean','_$ctxt'(_CallerModuleVar,8,__newcontextvar3)),','(FLORA_THIS_WORKSPACE(d^isa)(__model,'ContextClass','_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar9,8)),FLORA_THIS_WORKSPACE(d^mvd)(__model,defBy,__param,'_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar10,8))),null,'_$_$_flora''rule_enabled'(8,'ctxModel.flr',FLORA_THIS_MODULE_NAME),fllibexecute_delayed_calls([__model,__param],[__model]),true)).
?-(fllibinsrulesig(8,'ctxModel.flr','_$_$_flora''descr_vars',FLORA_THIS_MODULE_NAME,8,FLORA_THIS_WORKSPACE(imvdcondef)(__model,resolved,0,*,'_$ctxt'(_CallerModuleVar,8,__newcontextvar4)),','(FLORA_THIS_WORKSPACE(d^isa)(__model,'ContextClass','_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar9,8)),FLORA_THIS_WORKSPACE(d^mvd)(__model,defBy,__param,'_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar10,8))),null,'_$_$_flora''rule_enabled'(8,'ctxModel.flr',FLORA_THIS_MODULE_NAME),fllibexecute_delayed_calls([__model,__param],[__model]),true)).
?-(fllibinsrulesig(8,'ctxModel.flr','_$_$_flora''descr_vars',FLORA_THIS_MODULE_NAME,8,FLORA_THIS_WORKSPACE(d^imvdsig)(__model,specializes,__model,'_$ctxt'(_CallerModuleVar,8,__newcontextvar5)),','(FLORA_THIS_WORKSPACE(d^isa)(__model,'ContextClass','_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar9,8)),FLORA_THIS_WORKSPACE(d^mvd)(__model,defBy,__param,'_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar10,8))),null,'_$_$_flora''rule_enabled'(8,'ctxModel.flr',FLORA_THIS_MODULE_NAME),fllibexecute_delayed_calls([__model,__param],[__model]),true)).
?-(fllibinsrulesig(8,'ctxModel.flr','_$_$_flora''descr_vars',FLORA_THIS_MODULE_NAME,8,FLORA_THIS_WORKSPACE(imvdcondef)(__model,specializes,0,*,'_$ctxt'(_CallerModuleVar,8,__newcontextvar6)),','(FLORA_THIS_WORKSPACE(d^isa)(__model,'ContextClass','_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar9,8)),FLORA_THIS_WORKSPACE(d^mvd)(__model,defBy,__param,'_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar10,8))),null,'_$_$_flora''rule_enabled'(8,'ctxModel.flr',FLORA_THIS_MODULE_NAME),fllibexecute_delayed_calls([__model,__param],[__model]),true)).
?-(fllibinsrulesig(8,'ctxModel.flr','_$_$_flora''descr_vars',FLORA_THIS_MODULE_NAME,8,FLORA_THIS_WORKSPACE(d^imvdsig)(__model,__param,__param,'_$ctxt'(_CallerModuleVar,8,__newcontextvar7)),','(FLORA_THIS_WORKSPACE(d^isa)(__model,'ContextClass','_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar9,8)),FLORA_THIS_WORKSPACE(d^mvd)(__model,defBy,__param,'_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar10,8))),null,'_$_$_flora''rule_enabled'(8,'ctxModel.flr',FLORA_THIS_MODULE_NAME),null,true)).
?-(fllibinsrulesig(8,'ctxModel.flr','_$_$_flora''descr_vars',FLORA_THIS_MODULE_NAME,8,FLORA_THIS_WORKSPACE(imvdcondef)(__model,__param,0,*,'_$ctxt'(_CallerModuleVar,8,__newcontextvar8)),','(FLORA_THIS_WORKSPACE(d^isa)(__model,'ContextClass','_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar9,8)),FLORA_THIS_WORKSPACE(d^mvd)(__model,defBy,__param,'_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar10,8))),null,'_$_$_flora''rule_enabled'(8,'ctxModel.flr',FLORA_THIS_MODULE_NAME),null,true)).
?-(fllibinsrulesig(10,'ctxModel.flr','_$_$_flora''descr_vars',FLORA_THIS_MODULE_NAME,9,FLORA_THIS_WORKSPACE(d^imvdsig)(__param,covers,__param,'_$ctxt'(_CallerModuleVar,10,__newcontextvar1)),FLORA_THIS_WORKSPACE(d^isa)(__param,'Parameter','_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar5,10)),null,'_$_$_flora''rule_enabled'(10,'ctxModel.flr',FLORA_THIS_MODULE_NAME),null,true)).
?-(fllibinsrulesig(10,'ctxModel.flr','_$_$_flora''descr_vars',FLORA_THIS_MODULE_NAME,9,FLORA_THIS_WORKSPACE(imvdcondef)(__param,covers,0,*,'_$ctxt'(_CallerModuleVar,10,__newcontextvar2)),FLORA_THIS_WORKSPACE(d^isa)(__param,'Parameter','_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar5,10)),null,'_$_$_flora''rule_enabled'(10,'ctxModel.flr',FLORA_THIS_MODULE_NAME),null,true)).
?-(fllibinsrulesig(10,'ctxModel.flr','_$_$_flora''descr_vars',FLORA_THIS_MODULE_NAME,9,FLORA_THIS_WORKSPACE(d^imvdsig)(__param,valueRelationship,__param,'_$ctxt'(_CallerModuleVar,10,__newcontextvar3)),FLORA_THIS_WORKSPACE(d^isa)(__param,'Parameter','_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar5,10)),null,'_$_$_flora''rule_enabled'(10,'ctxModel.flr',FLORA_THIS_MODULE_NAME),null,true)).
?-(fllibinsrulesig(10,'ctxModel.flr','_$_$_flora''descr_vars',FLORA_THIS_MODULE_NAME,9,FLORA_THIS_WORKSPACE(imvdcondef)(__param,valueRelationship,0,*,'_$ctxt'(_CallerModuleVar,10,__newcontextvar4)),FLORA_THIS_WORKSPACE(d^isa)(__param,'Parameter','_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar5,10)),null,'_$_$_flora''rule_enabled'(10,'ctxModel.flr',FLORA_THIS_MODULE_NAME),null,true)).
?-(fllibinsrulesig(12,'ctxModel.flr','_$_$_flora''descr_vars',FLORA_THIS_MODULE_NAME,10,FLORA_THIS_WORKSPACE(d^imvdsig)(__param,flapply(detParamValue,__domainCase),__param,'_$ctxt'(_CallerModuleVar,12,__newcontextvar1)),','(FLORA_THIS_WORKSPACE(d^isa)(__param,'Parameter','_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar3,12)),FLORA_WORKSPACE(bc,d^isa)(__domainCase,'BusinessCaseClass','_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar4,12))),null,'_$_$_flora''rule_enabled'(12,'ctxModel.flr',FLORA_THIS_MODULE_NAME),null,true)).
?-(fllibinsrulesig(12,'ctxModel.flr','_$_$_flora''descr_vars',FLORA_THIS_MODULE_NAME,10,FLORA_THIS_WORKSPACE(imvdcondef)(__param,flapply(detParamValue,__domainCase),0,*,'_$ctxt'(_CallerModuleVar,12,__newcontextvar2)),','(FLORA_THIS_WORKSPACE(d^isa)(__param,'Parameter','_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar3,12)),FLORA_WORKSPACE(bc,d^isa)(__domainCase,'BusinessCaseClass','_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar4,12))),null,'_$_$_flora''rule_enabled'(12,'ctxModel.flr',FLORA_THIS_MODULE_NAME),null,true)).
?-(fllibinsrulesig(14,'ctxModel.flr','_$_$_flora''descr_vars',FLORA_THIS_MODULE_NAME,11,FLORA_THIS_WORKSPACE(d^isa)(__param,defBy,'_$ctxt'(_CallerModuleVar,14,__newcontextvar1)),FLORA_THIS_WORKSPACE(d^isa)(__param,'Parameter','_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar2,14)),null,'_$_$_flora''rule_enabled'(14,'ctxModel.flr',FLORA_THIS_MODULE_NAME),null,true)).
?-(fllibinsrulesig(16,'ctxModel.flr','_$_$_flora''descr_vars',FLORA_THIS_MODULE_NAME,12,FLORA_THIS_WORKSPACE(d^imvdsig)(__model,__param,__param,'_$ctxt'(_CallerModuleVar,16,__newcontextvar1)),','(FLORA_THIS_WORKSPACE(d^isa)(__model,'ContextClass','_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar3,16)),FLORA_THIS_WORKSPACE(d^isa)(__param,'Parameter','_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar4,16))),null,'_$_$_flora''rule_enabled'(16,'ctxModel.flr',FLORA_THIS_MODULE_NAME),null,true)).
?-(fllibinsrulesig(16,'ctxModel.flr','_$_$_flora''descr_vars',FLORA_THIS_MODULE_NAME,12,FLORA_THIS_WORKSPACE(imvdcondef)(__model,__param,0,*,'_$ctxt'(_CallerModuleVar,16,__newcontextvar2)),','(FLORA_THIS_WORKSPACE(d^isa)(__model,'ContextClass','_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar3,16)),FLORA_THIS_WORKSPACE(d^isa)(__param,'Parameter','_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar4,16))),null,'_$_$_flora''rule_enabled'(16,'ctxModel.flr',FLORA_THIS_MODULE_NAME),null,true)).
?-(fllibinsrulesig(18,'ctxModel.flr','_$_$_flora''descr_vars',FLORA_THIS_MODULE_NAME,13,FLORA_THIS_WORKSPACE(d^mvd)(__parent,covers,__ancestor,'_$ctxt'(_CallerModuleVar,18,__newcontextvar1)),','(FLORA_THIS_WORKSPACE(d^isa)(___value,'Parameter','_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar2,18)),','(','(FLORA_THIS_WORKSPACE(d^mvd)(__parent,covers,___child,'_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar4,18)),FLORA_THIS_WORKSPACE(d^isa)(__parent,___value,'_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar3,18))),','(','(FLORA_THIS_WORKSPACE(d^mvd)(___child,covers,__ancestor,'_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar7,18)),FLORA_THIS_WORKSPACE(d^isa)(__ancestor,___value,'_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar6,18))),FLORA_THIS_WORKSPACE(d^isa)(___child,___value,'_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar5,18))))),null,'_$_$_flora''rule_enabled'(18,'ctxModel.flr',FLORA_THIS_MODULE_NAME),fllibexecute_delayed_calls([___child,___value,__ancestor,__parent],[__ancestor,__parent]),true)).
?-(fllibinsrulesig(20,'ctxModel.flr','_$_$_flora''descr_vars',FLORA_THIS_MODULE_NAME,14,FLORA_THIS_WORKSPACE(d^mvd)(__parent,covers,__parent,'_$ctxt'(_CallerModuleVar,20,__newcontextvar1)),','(FLORA_THIS_WORKSPACE(d^isa)(__parent,___dim,'_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar2,20)),FLORA_THIS_WORKSPACE(d^isa)(___dim,'Parameter','_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar3,20))),null,'_$_$_flora''rule_enabled'(20,'ctxModel.flr',FLORA_THIS_MODULE_NAME),fllibexecute_delayed_calls([___dim,__parent],[__parent]),true)).
?-(fllibinsrulesig(22,'ctxModel.flr','_$_$_flora''descr_vars',FLORA_THIS_MODULE_NAME,15,FLORA_THIS_WORKSPACE(d^imvdsig)(__model,flapply(paramCovers,'Parameter'),__model,'_$ctxt'(_CallerModuleVar,22,__newcontextvar1)),FLORA_THIS_WORKSPACE(d^isa)(__model,'ContextClass','_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar3,22)),null,'_$_$_flora''rule_enabled'(22,'ctxModel.flr',FLORA_THIS_MODULE_NAME),null,true)).
?-(fllibinsrulesig(22,'ctxModel.flr','_$_$_flora''descr_vars',FLORA_THIS_MODULE_NAME,15,FLORA_THIS_WORKSPACE(imvdcondef)(__model,flapply(paramCovers,'Parameter'),0,*,'_$ctxt'(_CallerModuleVar,22,__newcontextvar2)),FLORA_THIS_WORKSPACE(d^isa)(__model,'ContextClass','_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar3,22)),null,'_$_$_flora''rule_enabled'(22,'ctxModel.flr',FLORA_THIS_MODULE_NAME),null,true)).
?-(fllibinsrulesig(24,'ctxModel.flr','_$_$_flora''descr_vars',FLORA_THIS_MODULE_NAME,16,FLORA_THIS_WORKSPACE(d^mvd)(__ctx,flapply(paramCovers,__param),__cov,'_$ctxt'(_CallerModuleVar,24,__newcontextvar1)),','(FLORA_THIS_WORKSPACE(d^isa)(__model,'ContextClass','_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar2,24)),','(','(FLORA_THIS_WORKSPACE(d^isa)(__ctx,__model,'_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar3,24)),FLORA_THIS_WORKSPACE(d^isa)(__cov,__model,'_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar4,24))),','(','(FLORA_THIS_WORKSPACE(d^isa)(__param,'Parameter','_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar5,24)),FLORA_THIS_WORKSPACE(d^mvd)(__ctx,__param,___v1,'_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar6,24))),','(','(FLORA_THIS_WORKSPACE(d^isa)(__param,'Parameter','_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar7,24)),FLORA_THIS_WORKSPACE(d^mvd)(__cov,__param,___v2,'_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar8,24))),FLORA_THIS_WORKSPACE(d^mvd)(___v1,covers,___v2,'_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar9,24)))))),null,'_$_$_flora''rule_enabled'(24,'ctxModel.flr',FLORA_THIS_MODULE_NAME),fllibexecute_delayed_calls([___v1,___v2,__cov,__ctx,__model,__param],[__cov,__ctx,__param]),true)).
?-(fllibinsrulesig(26,'ctxModel.flr','_$_$_flora''descr_vars',FLORA_THIS_MODULE_NAME,17,FLORA_THIS_WORKSPACE(d^mvd)(__cov,specialises,__ctx,'_$ctxt'(_CallerModuleVar,26,__newcontextvar1)),','(FLORA_THIS_WORKSPACE(d^isa)(__model,'ContextClass','_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar2,26)),','(','(FLORA_THIS_WORKSPACE(d^isa)(__ctx,__model,'_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar3,26)),FLORA_THIS_WORKSPACE(d^isa)(__cov,__model,'_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar4,26))),','(FLORA_THIS_WORKSPACE(d^mvd)(__ctx,flapply(paramCovers,__newdontcarevar5),__cov,'_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar6,26)),fllibtruthvalue(true,FLORA_THIS_MODULE_NAME,flibnafdelay(flora_naf(FLORA_THIS_WORKSPACE(tabled_unnumber_call)(','(','(','(FLORA_THIS_WORKSPACE(d^mvd)(__newdontcarevar7,defBy,__newquantvar11,'_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar9,26)),FLORA_THIS_WORKSPACE(d^isa)(__newdontcarevar7,'ContextClass','_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar8,26))),flibnafdelay(flora_naf(FLORA_THIS_WORKSPACE(tabled_unnumber_call)(','(FLORA_THIS_WORKSPACE(d^mvd)(__ctx,flapply(paramCovers,__newquantvar11),__cov,'_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar10,26)),fllibexecute_delayed_calls([__cov,__ctx,__newquantvar11],[]))),[__ctx,__newquantvar11,__cov],45,'ctxModel.flr'))),fllibexecute_delayed_calls([__cov,__ctx,__newquantvar11],[]))),[__ctx,__cov,__cov,__ctx,__ctx,__cov],45,'ctxModel.flr')))))),null,'_$_$_flora''rule_enabled'(26,'ctxModel.flr',FLORA_THIS_MODULE_NAME),fllibexecute_delayed_calls([__newdontcarevar12,__cov,__ctx,__model],[__cov,__ctx]),true)).
?-(fllibinsrulesig(28,'ctxModel.flr','_$_$_flora''descr_vars',FLORA_THIS_MODULE_NAME,18,FLORA_THIS_WORKSPACE(d^mvd)(__model,flapply(detRelevantCtxs,__bc),__ctx,'_$ctxt'(_CallerModuleVar,28,__newcontextvar1)),','(FLORA_THIS_WORKSPACE(d^isa)(__model,'ContextClass','_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar2,28)),','(FLORA_THIS_WORKSPACE(d^isa)(__ctx,__model,'_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar3,28)),fllibtruthvalue(true,FLORA_THIS_MODULE_NAME,flibnafdelay(flora_naf(FLORA_THIS_WORKSPACE(tabled_unnumber_call)(','(','(','(FLORA_THIS_WORKSPACE(d^isa)(__newdontcarevar4,'Parameter','_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar5,28)),FLORA_THIS_WORKSPACE(d^mvd)(__ctx,__newdontcarevar4,__newquantvar12,'_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar6,28))),flibnafdelay(flora_naf(FLORA_THIS_WORKSPACE(tabled_unnumber_call)(','(','(','(','(FLORA_THIS_WORKSPACE(d^mvd)(___d,flapply(detParamValue,__bc),__val,'_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar9,28)),FLORA_THIS_WORKSPACE(d^isa)(__val,___d,'_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar8,28))),FLORA_THIS_WORKSPACE(d^isa)(___d,'Parameter','_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar7,28))),','(FLORA_THIS_WORKSPACE(d^mvd)(__newquantvar12,covers,__val,'_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar11,28)),FLORA_THIS_WORKSPACE(d^isa)(__newquantvar12,___d,'_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar10,28)))),fllibexecute_delayed_calls([___d,__bc,__newquantvar12,__val],[]))),[__bc,__newquantvar12,__newquantvar12],53,'ctxModel.flr'))),fllibexecute_delayed_calls([___d,__bc,__ctx,__newquantvar12,__val],[]))),[__ctx,___d,__bc,__val,__val,___d,___d,__val,___d,___d,__bc,__val,__bc],53,'ctxModel.flr'))))),null,'_$_$_flora''rule_enabled'(28,'ctxModel.flr',FLORA_THIS_MODULE_NAME),fllibexecute_delayed_calls([__newdontcarevar13,___d,__bc,__ctx,__model,__val],[__bc,__ctx,__model]),true)).
?-(fllibinsrulesig(30,'ctxModel.flr','_$_$_flora''descr_vars',FLORA_THIS_MODULE_NAME,19,FLORA_THIS_WORKSPACE(d^tran)(__model,flapply(detCleanCaseSpecificCtx,__bc,__mTarget,__ctxf),'_$ctxt'(_CallerModuleVar,30,__newcontextvar1)),','(flliberasemodule(FLORA_THIS_MODULE_NAME,__mTarget),','(!,FLORA_THIS_WORKSPACE(d^tran)(__model,flapply(detCaseSpecificCtx,__bc,__mTarget,__ctxf),'_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar2,30)))),null,'_$_$_flora''rule_enabled'(30,'ctxModel.flr',FLORA_THIS_MODULE_NAME),null,true)).
?-(fllibinsrulesig(32,'ctxModel.flr','_$_$_flora''descr_vars',FLORA_THIS_MODULE_NAME,20,FLORA_THIS_WORKSPACE(d^tran)(__model,flapply(detCaseSpecificCtx,__bc,__mTarget,__ctxf),'_$ctxt'(_CallerModuleVar,32,__newcontextvar1)),','(FLORA_THIS_WORKSPACE(d^isa)(__model,'ContextClass','_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar2,32)),','(FLORA_THIS_WORKSPACE(d^mvd)(__model,flapply(detRelevantCtxs,__bc),__ctx,'_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar3,32)),','(FLORA_THIS_WORKSPACE(d^mvd)(__ctx,file,__ctxf,'_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar4,32)),fllibhypothetical(<>,fllibload([>>(+(__ctxf),__mTarget)],'C:/TEMP/dke/flora2/CBRM/ctxModelAIM.flr',FLORA_THIS_MODULE_NAME,[65,35]))))),null,'_$_$_flora''rule_enabled'(32,'ctxModel.flr',FLORA_THIS_MODULE_NAME),fllibexecute_delayed_calls([__bc,__ctx,__ctxf,__mTarget,__model],[__bc,__ctxf,__mTarget,__model]),true)).
?-(fllibinsrulesig(34,'ctxModel.flr','_$_$_flora''descr_vars',FLORA_THIS_MODULE_NAME,21,FLORA_THIS_WORKSPACE(d^tran)(__model,delete,'_$ctxt'(_CallerModuleVar,34,__newcontextvar1)),','(FLORA_THIS_WORKSPACE(d^isa)(__model,'ContextClass','_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar2,34)),','(flliberaseall(FLORA_THIS_MODULE_NAME,[flsysdbupdate(FLORA_THIS_WORKSPACE(mvd)(__newdontcarevar3,__newdontcarevar4,__newdontcarevar5,'_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar7,__newcontextvar6)),FLORA_THIS_FDB_STORAGE,FLORA_THIS_MODULE_NAME),flsysdbupdate(FLORA_THIS_WORKSPACE(isa)(__newdontcarevar8,__model,'_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar10,__newcontextvar9)),FLORA_THIS_FDB_STORAGE,FLORA_THIS_MODULE_NAME)]),','(flliberaseall(FLORA_THIS_MODULE_NAME,[flsysdbupdate(FLORA_THIS_WORKSPACE(isa)(__newdontcarevar11,__param,'_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar13,__newcontextvar12)),FLORA_THIS_FDB_STORAGE,FLORA_THIS_MODULE_NAME),flsysdbupdate(FLORA_THIS_WORKSPACE(isa)(__param,'Parameter','_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar15,__newcontextvar14)),FLORA_THIS_FDB_STORAGE,FLORA_THIS_MODULE_NAME)]),flliberaseall(FLORA_THIS_MODULE_NAME,[flsysdbupdate(FLORA_THIS_WORKSPACE(isa)(__model,'ContextClass','_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar17,__newcontextvar16)),FLORA_THIS_FDB_STORAGE,FLORA_THIS_MODULE_NAME)])))),null,'_$_$_flora''rule_enabled'(34,'ctxModel.flr',FLORA_THIS_MODULE_NAME),fllibexecute_delayed_calls([__newdontcarevar18,__model,__param],[__model]),true)).
?-(fllibinsrulesig(36,'ctxModel.flr','_$_$_flora''descr_vars',FLORA_THIS_MODULE_NAME,22,FLORA_THIS_WORKSPACE(d^tran)(__model,flapply(addParam,__param,__rootVal,__ctx,__detParamVal),'_$ctxt'(_CallerModuleVar,36,__newcontextvar1)),','(FLORA_THIS_WORKSPACE(d^isa)(__model,'ContextClass','_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar2,36)),','(FLORA_THIS_WORKSPACE(d^isa)(__ctx,__model,'_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar3,36)),','(fllibbtinsert(FLORA_THIS_MODULE_NAME,[flsysdbupdate(FLORA_THIS_WORKSPACE(isa)(__param,'Parameter','_$ctxt'(_CallerModuleVar,__newcontextvar4,__newcontextvar5)),FLORA_THIS_FDB_STORAGE,FLORA_THIS_MODULE_NAME)]),','(fllibinsert(FLORA_THIS_MODULE_NAME,[__detParamVal]),fllibbtinsertall(FLORA_THIS_MODULE_NAME,[flsysdbupdate(FLORA_THIS_WORKSPACE(isa)(__rootVal,__param,'_$ctxt'(_CallerModuleVar,__newcontextvar6,__newcontextvar7)),FLORA_THIS_FDB_STORAGE,FLORA_THIS_MODULE_NAME),flsysdbupdate(FLORA_THIS_WORKSPACE(mvd)(__model,defBy,__param,'_$ctxt'(_CallerModuleVar,__newcontextvar8,__newcontextvar9)),FLORA_THIS_FDB_STORAGE,FLORA_THIS_MODULE_NAME),flsysdbupdate(FLORA_THIS_WORKSPACE(mvd)(__ctx,__param,__rootVal,'_$ctxt'(_CallerModuleVar,__newcontextvar10,__newcontextvar11)),FLORA_THIS_FDB_STORAGE,FLORA_THIS_MODULE_NAME)]))))),null,'_$_$_flora''rule_enabled'(36,'ctxModel.flr',FLORA_THIS_MODULE_NAME),null,true)).
?-(fllibinsrulesig(38,'ctxModel.flr','_$_$_flora''descr_vars',FLORA_THIS_MODULE_NAME,23,FLORA_THIS_WORKSPACE(d^tran)(__model,flapply(delParam,__param,__id),'_$ctxt'(_CallerModuleVar,38,__newcontextvar1)),','(FLORA_THIS_WORKSPACE(d^isa)(__model,'ContextClass','_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar2,38)),','(fllibbtdeleteall(FLORA_THIS_MODULE_NAME,[flsysdbupdate(FLORA_THIS_WORKSPACE(isa)(__param,'Parameter','_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar4,__newcontextvar3)),FLORA_THIS_FDB_STORAGE,FLORA_THIS_MODULE_NAME),flsysdbupdate(FLORA_THIS_WORKSPACE(mvd)(__newdontcarevar5,defBy,__param,'_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar7,__newcontextvar6)),FLORA_THIS_FDB_STORAGE,FLORA_THIS_MODULE_NAME),flsysdbupdate(FLORA_THIS_WORKSPACE(isa)(__param,'Parameter','_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar9,__newcontextvar8)),FLORA_THIS_FDB_STORAGE,FLORA_THIS_MODULE_NAME)]),','(fllibclause(__id,__newvar10,','('_$_$_flora''prop_descriptor'(__id,__newvar11,__newvar12,type,__newvar14,__newvar10),'_$_$_flora''tag_descriptor'(__id,__newvar11,__newvar12,__newvar13,__newvar10)),null,[fllibmodlit('d^mvd',[__param,flapply(detParamValue,__newdontcarevar15),__newdontcarevar16,'_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar17,38)],__newdontcarevar18,'_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar19,38))],__newdontcarevar20),','(:(flrdescriptor_support,disable_rule_by_id(__id,__newdontcarevar21,FLORA_THIS_MODULE_NAME)),','(fllibbtdeleteall(FLORA_THIS_MODULE_NAME,[flsysdbupdate(FLORA_THIS_WORKSPACE(isa)(__newdontcarevar22,__param,'_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar24,__newcontextvar23)),FLORA_THIS_FDB_STORAGE,FLORA_THIS_MODULE_NAME)]),','(fllibbtdeleteall(FLORA_THIS_MODULE_NAME,[flsysdbupdate(FLORA_THIS_WORKSPACE(mvd)(__x,covers,__y,'_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar26,__newcontextvar25)),FLORA_THIS_FDB_STORAGE,FLORA_THIS_MODULE_NAME),flsysdbupdate(FLORA_THIS_WORKSPACE(isa)(__x,__param,'_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar28,__newcontextvar27)),FLORA_THIS_FDB_STORAGE,FLORA_THIS_MODULE_NAME),flsysdbupdate(FLORA_THIS_WORKSPACE(isa)(__y,__param,'_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar30,__newcontextvar29)),FLORA_THIS_FDB_STORAGE,FLORA_THIS_MODULE_NAME)]),fllibbtdeleteall(FLORA_THIS_MODULE_NAME,[flsysdbupdate(FLORA_THIS_WORKSPACE(mvd)(__newdontcarevar31,__param,__newdontcarevar32,'_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar34,__newcontextvar33)),FLORA_THIS_FDB_STORAGE,FLORA_THIS_MODULE_NAME)]))))))),null,'_$_$_flora''rule_enabled'(38,'ctxModel.flr',FLORA_THIS_MODULE_NAME),fllibexecute_delayed_calls([__newdontcarevar35,__id,__model,__param,__x,__y],[__id,__model,__param]),true)).
?-(fllibinsrulesig(40,'ctxModel.flr','_$_$_flora''descr_vars',FLORA_THIS_MODULE_NAME,24,FLORA_THIS_WORKSPACE(d^tran)(__model,flapply(addValueLeaf,__parent,__newVal),'_$ctxt'(_CallerModuleVar,40,__newcontextvar1)),','(FLORA_THIS_WORKSPACE(d^isa)(__model,'ContextClass','_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar2,40)),','(FLORA_THIS_WORKSPACE(d^isa)(__parent,__param,'_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar3,40)),','(FLORA_THIS_WORKSPACE(d^isa)(__param,'Parameter','_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar4,40)),','(fllibbtinsert(FLORA_THIS_MODULE_NAME,[flsysdbupdate(FLORA_THIS_WORKSPACE(isa)(__newVal,__param,'_$ctxt'(_CallerModuleVar,__newcontextvar5,__newcontextvar6)),FLORA_THIS_FDB_STORAGE,FLORA_THIS_MODULE_NAME)]),fllibbtinsert(FLORA_THIS_MODULE_NAME,[flsysdbupdate(FLORA_THIS_WORKSPACE(mvd)(__parent,covers,__newVal,'_$ctxt'(_CallerModuleVar,__newcontextvar7,__newcontextvar8)),FLORA_THIS_FDB_STORAGE,FLORA_THIS_MODULE_NAME)]))))),null,'_$_$_flora''rule_enabled'(40,'ctxModel.flr',FLORA_THIS_MODULE_NAME),fllibexecute_delayed_calls([__model,__newVal,__param,__parent],[__model,__newVal,__parent]),true)).
?-(fllibinsrulesig(42,'ctxModel.flr','_$_$_flora''descr_vars',FLORA_THIS_MODULE_NAME,25,FLORA_THIS_WORKSPACE(d^tran)(__model,flapply(addValueNode,__parent,__newVal,__child),'_$ctxt'(_CallerModuleVar,42,__newcontextvar1)),','(FLORA_THIS_WORKSPACE(d^isa)(__model,'ContextClass','_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar2,42)),','(FLORA_THIS_WORKSPACE(d^isa)(__parent,__param,'_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar3,42)),','(FLORA_THIS_WORKSPACE(d^isa)(__param,'Parameter','_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar4,42)),','(fllibbtinsert(FLORA_THIS_MODULE_NAME,[flsysdbupdate(FLORA_THIS_WORKSPACE(isa)(__newVal,__param,'_$ctxt'(_CallerModuleVar,__newcontextvar5,__newcontextvar6)),FLORA_THIS_FDB_STORAGE,FLORA_THIS_MODULE_NAME)]),','(fllibbtinsert(FLORA_THIS_MODULE_NAME,[flsysdbupdate(FLORA_THIS_WORKSPACE(mvd)(__parent,covers,__newVal,'_$ctxt'(_CallerModuleVar,__newcontextvar7,__newcontextvar8)),FLORA_THIS_FDB_STORAGE,FLORA_THIS_MODULE_NAME),flsysdbupdate(FLORA_THIS_WORKSPACE(mvd)(__newVal,covers,__child,'_$ctxt'(_CallerModuleVar,__newcontextvar9,__newcontextvar10)),FLORA_THIS_FDB_STORAGE,FLORA_THIS_MODULE_NAME)]),fllibbtdeleteall(FLORA_THIS_MODULE_NAME,[flsysdbupdate(FLORA_THIS_WORKSPACE(mvd)(__parent,covers,__child,'_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar12,__newcontextvar11)),FLORA_THIS_FDB_STORAGE,FLORA_THIS_MODULE_NAME)])))))),null,'_$_$_flora''rule_enabled'(42,'ctxModel.flr',FLORA_THIS_MODULE_NAME),fllibexecute_delayed_calls([__child,__model,__newVal,__param,__parent],[__child,__model,__newVal,__parent]),true)).
?-(fllibinsrulesig(44,'ctxModel.flr','_$_$_flora''descr_vars',FLORA_THIS_MODULE_NAME,26,FLORA_THIS_WORKSPACE(d^tran)(__model,flapply(addValueRoot,__newVal,__child),'_$ctxt'(_CallerModuleVar,44,__newcontextvar1)),','(FLORA_THIS_WORKSPACE(d^isa)(__model,'ContextClass','_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar2,44)),','(FLORA_THIS_WORKSPACE(d^isa)(__child,__param,'_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar3,44)),','(FLORA_THIS_WORKSPACE(d^isa)(__param,'Parameter','_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar4,44)),','(fllibbtinsert(FLORA_THIS_MODULE_NAME,[flsysdbupdate(FLORA_THIS_WORKSPACE(isa)(__newVal,__param,'_$ctxt'(_CallerModuleVar,__newcontextvar5,__newcontextvar6)),FLORA_THIS_FDB_STORAGE,FLORA_THIS_MODULE_NAME)]),fllibbtinsert(FLORA_THIS_MODULE_NAME,[flsysdbupdate(FLORA_THIS_WORKSPACE(mvd)(__newVal,covers,__child,'_$ctxt'(_CallerModuleVar,__newcontextvar7,__newcontextvar8)),FLORA_THIS_FDB_STORAGE,FLORA_THIS_MODULE_NAME)]))))),null,'_$_$_flora''rule_enabled'(44,'ctxModel.flr',FLORA_THIS_MODULE_NAME),fllibexecute_delayed_calls([__child,__model,__newVal,__param],[__child,__model,__newVal]),true)).
?-(fllibinsrulesig(46,'ctxModel.flr','_$_$_flora''descr_vars',FLORA_THIS_MODULE_NAME,27,FLORA_THIS_WORKSPACE(d^tran)(__model,flapply(delValueSubGraph,__val),'_$ctxt'(_CallerModuleVar,46,__newcontextvar1)),','(FLORA_THIS_WORKSPACE(d^isa)(__model,'ContextClass','_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar2,46)),','(fllibbtdeleteall(FLORA_THIS_MODULE_NAME,[flsysdbupdate(FLORA_THIS_WORKSPACE(mvd)(__newdontcarevar3,covers,__val,'_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar5,__newcontextvar4)),FLORA_THIS_FDB_STORAGE,FLORA_THIS_MODULE_NAME)]),flliberase(FLORA_THIS_MODULE_NAME,[flsysdbupdate(FLORA_THIS_WORKSPACE(isa)(__val,__newdontcarevar6,'_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar8,__newcontextvar7)),FLORA_THIS_FDB_STORAGE,FLORA_THIS_MODULE_NAME)]))),null,'_$_$_flora''rule_enabled'(46,'ctxModel.flr',FLORA_THIS_MODULE_NAME),fllibexecute_delayed_calls([__newdontcarevar9,__model,__val],[__model,__val]),true)).
?-(fllibinsrulesig(48,'ctxModel.flr','_$_$_flora''descr_vars',FLORA_THIS_MODULE_NAME,28,FLORA_THIS_WORKSPACE(d^tran)(__model,flapply(delValue,__val,__parent),'_$ctxt'(_CallerModuleVar,48,__newcontextvar1)),','(FLORA_THIS_WORKSPACE(d^isa)(__model,'ContextClass','_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar2,48)),','(FLORA_THIS_WORKSPACE(d^mvd)(__parent,covers,__val,'_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar3,48)),','(flibnafdelay(flora_naf(FLORA_THIS_WORKSPACE(tabled_unnumber_call)(','(=(__parent,__val),fllibexecute_delayed_calls([__parent,__val],[]))),[__parent,__val],131,'ctxModel.flr')),','(FLORA_THIS_WORKSPACE(d^mvd)(__val,covers,__children,'_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar4,48)),','(flibnafdelay(flora_naf(FLORA_THIS_WORKSPACE(tabled_unnumber_call)(','(=(__val,__children),fllibexecute_delayed_calls([__children,__val],[]))),[__val,__children],133,'ctxModel.flr')),','(fllibbtdeleteall(FLORA_THIS_MODULE_NAME,[flsysdbupdate(FLORA_THIS_WORKSPACE(mvd)(__newdontcarevar5,covers,__val,'_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar7,__newcontextvar6)),FLORA_THIS_FDB_STORAGE,FLORA_THIS_MODULE_NAME)]),','(fllibbtdeleteall(FLORA_THIS_MODULE_NAME,[flsysdbupdate(FLORA_THIS_WORKSPACE(mvd)(__val,covers,__newdontcarevar8,'_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar10,__newcontextvar9)),FLORA_THIS_FDB_STORAGE,FLORA_THIS_MODULE_NAME)]),','(fllibbtdeleteall(FLORA_THIS_MODULE_NAME,[flsysdbupdate(FLORA_THIS_WORKSPACE(isa)(__val,__newdontcarevar11,'_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar13,__newcontextvar12)),FLORA_THIS_FDB_STORAGE,FLORA_THIS_MODULE_NAME)]),fllibbtinsertall(FLORA_THIS_MODULE_NAME,[flsysdbupdate(FLORA_THIS_WORKSPACE(mvd)(__parent,covers,__children,'_$ctxt'(_CallerModuleVar,__newcontextvar14,__newcontextvar15)),FLORA_THIS_FDB_STORAGE,FLORA_THIS_MODULE_NAME)]))))))))),null,'_$_$_flora''rule_enabled'(48,'ctxModel.flr',FLORA_THIS_MODULE_NAME),fllibexecute_delayed_calls([__newdontcarevar16,__children,__model,__parent,__val],[__model,__parent,__val]),true)).
?-(fllibinsrulesig(50,'ctxModel.flr','_$_$_flora''descr_vars',FLORA_THIS_MODULE_NAME,29,FLORA_THIS_WORKSPACE(d^tran)(__model,delCtxByValue,'_$ctxt'(_CallerModuleVar,50,__newcontextvar1)),','(FLORA_THIS_WORKSPACE(d^isa)(__model,'ContextClass','_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar2,50)),fllibbtdeleteall(FLORA_THIS_MODULE_NAME,[flsysdbupdate(FLORA_THIS_WORKSPACE(mvd)(__ctx,__newdontcarevar3,__newdontcarevar4,'_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar6,__newcontextvar5)),FLORA_THIS_FDB_STORAGE,FLORA_THIS_MODULE_NAME),flsysdbupdate(FLORA_THIS_WORKSPACE(isa)(__ctx,__model,'_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar8,__newcontextvar7)),FLORA_THIS_FDB_STORAGE,FLORA_THIS_MODULE_NAME)],','(','(FLORA_THIS_WORKSPACE(d^mvd)(__ctx,__param,__val,'_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar10,50)),FLORA_THIS_WORKSPACE(d^isa)(__ctx,__model,'_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar9,50))),','(FLORA_THIS_WORKSPACE(d^isa)(__param,'Parameter','_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar11,50)),flibnafdelay(flora_naf(FLORA_THIS_WORKSPACE(tabled_unnumber_call)(','(FLORA_THIS_WORKSPACE(d^isa)(__val,__param,'_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar12,50)),fllibexecute_delayed_calls([__param,__val],[]))),[__val,__param],142,'ctxModel.flr')))))),null,'_$_$_flora''rule_enabled'(50,'ctxModel.flr',FLORA_THIS_MODULE_NAME),fllibexecute_delayed_calls([__newdontcarevar13,__ctx,__model,__param,__val],[__model]),true)).
?-(fllibinsrulesig(52,'ctxModel.flr','_$_$_flora''descr_vars',FLORA_THIS_MODULE_NAME,30,FLORA_THIS_WORKSPACE(d^tran)(__model,flapply(modCtxByValue,__old,__newVal,__ctx),'_$ctxt'(_CallerModuleVar,52,__newcontextvar1)),','(FLORA_THIS_WORKSPACE(d^isa)(__model,'ContextClass','_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar2,52)),','(','(FLORA_THIS_WORKSPACE(d^mvd)(__ctx,__param,__old,'_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar4,52)),FLORA_THIS_WORKSPACE(d^isa)(__ctx,__model,'_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar3,52))),','(FLORA_THIS_WORKSPACE(d^isa)(__newVal,__param,'_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar5,52)),','(fllibbtinsertall(FLORA_THIS_MODULE_NAME,[flsysdbupdate(FLORA_THIS_WORKSPACE(mvd)(__ctx,__param,__newVal,'_$ctxt'(_CallerModuleVar,__newcontextvar6,__newcontextvar7)),FLORA_THIS_FDB_STORAGE,FLORA_THIS_MODULE_NAME)]),fllibbtdeleteall(FLORA_THIS_MODULE_NAME,[flsysdbupdate(FLORA_THIS_WORKSPACE(mvd)(__ctx,__newdontcarevar8,__old,'_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar10,__newcontextvar9)),FLORA_THIS_FDB_STORAGE,FLORA_THIS_MODULE_NAME)]))))),null,'_$_$_flora''rule_enabled'(52,'ctxModel.flr',FLORA_THIS_MODULE_NAME),fllibexecute_delayed_calls([__newdontcarevar11,__ctx,__model,__newVal,__old,__param],[__ctx,__model,__newVal,__old]),true)).
?-(fllibinsrulesig(54,'ctxModel.flr','_$_$_flora''descr_vars',FLORA_THIS_MODULE_NAME,31,FLORA_THIS_WORKSPACE(d^tran)(__ctx,flapply(situationalModification,__ctxTarget,__param),'_$ctxt'(_CallerModuleVar,54,__newcontextvar1)),','(FLORA_THIS_WORKSPACE(d^isa)(__ctx,'AIMCtx','_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar2,54)),','(FLORA_THIS_WORKSPACE(d^isa)(__ctxTarget,'AIMCtx','_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar3,54)),','(fllibbtdeleteall(FLORA_THIS_MODULE_NAME,[flsysdbupdate(FLORA_THIS_WORKSPACE(mvd)(__ctx,__newdontcarevar4,__newdontcarevar5,'_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar7,__newcontextvar6)),FLORA_THIS_FDB_STORAGE,FLORA_THIS_MODULE_NAME)]),','(','(FLORA_THIS_WORKSPACE(d^isa)(__param,'Parameter','_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar8,54)),FLORA_THIS_WORKSPACE(d^mvd)(__ctxTarget,__param,__new,'_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar9,54))),fllibbtinsertall(FLORA_THIS_MODULE_NAME,[flsysdbupdate(FLORA_THIS_WORKSPACE(mvd)(__ctx,__param,__new,'_$ctxt'(_CallerModuleVar,__newcontextvar10,__newcontextvar11)),FLORA_THIS_FDB_STORAGE,FLORA_THIS_MODULE_NAME)]))))),null,'_$_$_flora''rule_enabled'(54,'ctxModel.flr',FLORA_THIS_MODULE_NAME),fllibexecute_delayed_calls([__newdontcarevar12,__ctx,__ctxTarget,__new,__param],[__ctx,__ctxTarget,__param]),true)).
?-(fllibinsrulesig(56,'ctxModel.flr','_$_$_flora''descr_vars',FLORA_THIS_MODULE_NAME,32,FLORA_THIS_WORKSPACE(d^tran)(__model,flapply(addCtx,__ctx),'_$ctxt'(_CallerModuleVar,56,__newcontextvar1)),','(FLORA_THIS_WORKSPACE(d^isa)(__model,'ContextClass','_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar2,56)),fllibbtinsert(FLORA_THIS_MODULE_NAME,[__ctx])),null,'_$_$_flora''rule_enabled'(56,'ctxModel.flr',FLORA_THIS_MODULE_NAME),null,true)).
?-(fllibinsrulesig(58,'ctxModel.flr','_$_$_flora''descr_vars',FLORA_THIS_MODULE_NAME,33,FLORA_THIS_WORKSPACE(d^tran)(__model,flapply(delCtx,__ctx),'_$ctxt'(_CallerModuleVar,58,__newcontextvar1)),','(FLORA_THIS_WORKSPACE(d^isa)(__model,'ContextClass','_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar2,58)),','(fllibbtdeleteall(FLORA_THIS_MODULE_NAME,[flsysdbupdate(FLORA_THIS_WORKSPACE(mvd)(__ctx,__newdontcarevar3,__newdontcarevar4,'_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar6,__newcontextvar5)),FLORA_THIS_FDB_STORAGE,FLORA_THIS_MODULE_NAME)],','(FLORA_THIS_WORKSPACE(d^isa)(__ctx,___x,'_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar7,58)),FLORA_THIS_WORKSPACE(d^isa)(___x,'ContextClass','_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar8,58)))),fllibbtdeleteall(FLORA_THIS_MODULE_NAME,[flsysdbupdate(FLORA_THIS_WORKSPACE(isa)(__ctx,__model,'_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar10,__newcontextvar9)),FLORA_THIS_FDB_STORAGE,FLORA_THIS_MODULE_NAME)]))),null,'_$_$_flora''rule_enabled'(58,'ctxModel.flr',FLORA_THIS_MODULE_NAME),fllibexecute_delayed_calls([__newdontcarevar11,___x,__ctx,__model],[__ctx,__model]),true)).
?-(fllibinsrulesig(60,'ctxModel.flr','_$_$_flora''descr_vars',FLORA_THIS_MODULE_NAME,34,FLORA_THIS_WORKSPACE(d^mvd)(__model,detUnusedValues,__v,'_$ctxt'(_CallerModuleVar,60,__newcontextvar1)),','(FLORA_THIS_WORKSPACE(d^isa)(__model,'ContextClass','_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar2,60)),','(FLORA_THIS_WORKSPACE(d^isa)(__v,___param,'_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar3,60)),','(FLORA_THIS_WORKSPACE(d^isa)(___param,'Parameter','_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar4,60)),';'(flibnafdelay(flora_naf(FLORA_THIS_WORKSPACE(tabled_unnumber_call)(','(FLORA_THIS_WORKSPACE(d^mvd)(__newdontcarevar5,___param,__v,'_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar7,60)),fllibexecute_delayed_calls([___param,__v],[]))),[___param,__v,__model],175,'ctxModel.flr')),flibnafdelay(flora_naf(FLORA_THIS_WORKSPACE(tabled_unnumber_call)(','(FLORA_THIS_WORKSPACE(d^isa)(__newdontcarevar5,__model,'_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar6,60)),fllibexecute_delayed_calls([__model],[]))),[___param,__v,__model],175,'ctxModel.flr')))))),null,'_$_$_flora''rule_enabled'(60,'ctxModel.flr',FLORA_THIS_MODULE_NAME),fllibexecute_delayed_calls([__newdontcarevar8,___param,__model,__v],[__model,__v]),true)).
?-(fllibinsrulesig(62,'ctxModel.flr','_$_$_flora''descr_vars',FLORA_THIS_MODULE_NAME,35,FLORA_THIS_WORKSPACE(d^tran)(__ctx,flapply(addTermRule,__rt),'_$ctxt'(_CallerModuleVar,62,__newcontextvar1)),','(FLORA_THIS_WORKSPACE(d^isa)(__ctx,__model,'_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar2,62)),','(FLORA_THIS_WORKSPACE(d^isa)(__model,'ContextClass','_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar3,62)),','(FLORA_THIS_WORKSPACE(d^mvd)(__ctx,file,__ctxf,'_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar4,62)),','(FLORA_WORKSPACE(\\io,d^meth)(__ctxf,flapply(open,append,__Stream),'_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar5,62)),','(FLORA_WORKSPACE(\\io,d^meth)(__Stream,flapply(write,__rt),'_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar6,62)),FLORA_WORKSPACE(\\io,d^meth)(__Stream,close,'_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar7,62))))))),null,'_$_$_flora''rule_enabled'(62,'ctxModel.flr',FLORA_THIS_MODULE_NAME),fllibexecute_delayed_calls([__Stream,__ctx,__ctxf,__model,__rt],[__ctx,__rt]),true)).
?-(fllibinsrulesig(64,'ctxModel.flr','_$_$_flora''descr_vars',FLORA_THIS_MODULE_NAME,36,FLORA_THIS_WORKSPACE(d^tran)(__ctx,flapply(delTermRule,__rt),'_$ctxt'(_CallerModuleVar,64,__newcontextvar1)),','(FLORA_THIS_WORKSPACE(d^isa)(__ctx,__model,'_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar2,64)),','(FLORA_THIS_WORKSPACE(d^isa)(__model,'ContextClass','_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar3,64)),','(FLORA_THIS_WORKSPACE(d^mvd)(__ctx,file,__ctxf,'_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar4,64)),','(FLORA_WORKSPACE(\\io,d^meth)(__ctxf,flapply(open,read,__Stream),'_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar5,64)),','(FLORA_WORKSPACE(\\io,d^meth)('CCtx/Contexts/tmp.tmp',flapply(open,write,__s),'_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar6,64)),','(fllibwhiledo(FLORA_THIS_MODULE_NAME,'\\repeat'(FLORA_WORKSPACE(\\io,d^meth)(__Stream,flapply(readline,atom,__Line),'_$ctxt'(__newcontextvar7,__newcontextvar9,__newcontextvar8))),fllibifthenelse(FLORA_THIS_MODULE_NAME,=(__rt,__Line),FLORA_WORKSPACE(\\io,d^meth)(__Stream,flapply(readline,atom,__newdontcarevar10),'_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar11,64)),FLORA_WORKSPACE(\\io,d^meth)(__s,flapply(write,__Line),'_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar12,64)),191,'ctxModel.flr'),190,'ctxModel.flr'),','(FLORA_WORKSPACE(\\io,d^meth)(__Stream,close,'_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar13,64)),','(FLORA_WORKSPACE(\\io,d^meth)(__s,close,'_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar14,64)),','(FLORA_WORKSPACE(\\io,d^meth)('File',flapply(remove,__ctxf),'_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar15,64)),FLORA_WORKSPACE(\\io,d^meth)('File',flapply(rename,'CCtx/Contexts/tmp.tmp',__ctxf),'_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar16,64))))))))))),null,'_$_$_flora''rule_enabled'(64,'ctxModel.flr',FLORA_THIS_MODULE_NAME),fllibexecute_delayed_calls([__Line,__Stream,__newdontcarevar17,__ctx,__ctxf,__model,__rt,__s],[__ctx,__rt]),true)).
?-(fllibinsrulesig(66,'ctxModelAIM.flr','_$_$_flora''descr_vars',FLORA_THIS_MODULE_NAME,42,FLORA_THIS_WORKSPACE(d^mvd)('Interest',flapply(detParamValue,__bc),__val,'_$ctxt'(_CallerModuleVar,66,__newcontextvar1)),','(FLORA_THIS_WORKSPACE(d^isa)(__val,'Interest','_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar2,66)),','(','(','(FLORA_WORKSPACE(bc,d^mvd)(__bc,interestSpec,__newdontcarevar4,'_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar6,66)),FLORA_WORKSPACE(bc,d^mvd)(__newdontcarevar4,interest,__aircraft,'_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar5,66))),FLORA_WORKSPACE(bc,d^isa)(__bc,'SemNOTAMCase','_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar3,66))),FLORA_THIS_WORKSPACE(d^tblflapply)(getAircraftType,__aircraft,__val,'_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar7,66)))),null,'_$_$_flora''rule_enabled'(66,'ctxModelAIM.flr',FLORA_THIS_MODULE_NAME),fllibexecute_delayed_calls([__newdontcarevar8,__aircraft,__bc,__val],[__bc,__val]),true)).
?-(fllibinsrulesig(68,'ctxModelAIM.flr','_$_$_flora''descr_vars',FLORA_THIS_MODULE_NAME,45,FLORA_THIS_WORKSPACE(d^mvd)('FlightPhase',flapply(detParamValue,__bc),__val,'_$ctxt'(_CallerModuleVar,68,__newcontextvar1)),','(FLORA_THIS_WORKSPACE(d^isa)(__val,'FlightPhase','_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar2,68)),','(','(FLORA_WORKSPACE(bc,d^mvd)(__bc,interestSpec,__newdontcarevar4,'_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar6,68)),FLORA_WORKSPACE(bc,d^mvd)(__newdontcarevar4,flightPhase,__val,'_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar5,68))),FLORA_WORKSPACE(bc,d^isa)(__bc,'SemNOTAMCase','_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar3,68)))),null,'_$_$_flora''rule_enabled'(68,'ctxModelAIM.flr',FLORA_THIS_MODULE_NAME),fllibexecute_delayed_calls([__newdontcarevar7,__bc,__val],[__bc,__val]),true)).
?-(fllibinsrulesig(70,'ctxModelAIM.flr','_$_$_flora''descr_vars',FLORA_THIS_MODULE_NAME,46,FLORA_THIS_WORKSPACE(d^mvd)('EventScenario',flapply(detParamValue,__bc),__val,'_$ctxt'(_CallerModuleVar,70,__newcontextvar1)),','(FLORA_THIS_WORKSPACE(d^isa)(__val,'EventScenario','_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar2,70)),','(','(','(FLORA_WORKSPACE(bc,d^mvd)(__bc,notam,__n,'_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar5,70)),FLORA_WORKSPACE(bc,d^isa)(__n,'NOTAM','_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar4,70))),FLORA_WORKSPACE(bc,d^isa)(__bc,'SemNOTAMCase','_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar3,70))),','(','(FLORA_WORKSPACE(bc,d^mvd)(__n,feature,runway,'_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar6,70)),FLORA_WORKSPACE(bc,d^mvd)(__n,status,closed,'_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar7,70))),=(__val,runwayClosure)))),null,'_$_$_flora''rule_enabled'(70,'ctxModelAIM.flr',FLORA_THIS_MODULE_NAME),fllibexecute_delayed_calls([__bc,__n,__val],[__bc,__val]),true)).
?-(fllibinsrulesig(72,'ctxModelAIM.flr','_$_$_flora''descr_vars',FLORA_THIS_MODULE_NAME,47,FLORA_THIS_WORKSPACE(d^mvd)('EventScenario',flapply(detParamValue,__bc),__val,'_$ctxt'(_CallerModuleVar,72,__newcontextvar1)),','(FLORA_THIS_WORKSPACE(d^isa)(__val,'EventScenario','_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar2,72)),','(','(','(FLORA_WORKSPACE(bc,d^mvd)(__bc,notam,__n,'_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar5,72)),FLORA_WORKSPACE(bc,d^isa)(__n,'NOTAM','_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar4,72))),FLORA_WORKSPACE(bc,d^isa)(__bc,'SemNOTAMCase','_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar3,72))),','(','(FLORA_WORKSPACE(bc,d^mvd)(__n,feature,runway,'_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar6,72)),FLORA_WORKSPACE(bc,d^mvd)(__n,status,limited,'_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar7,72))),=(__val,runwayClosure)))),null,'_$_$_flora''rule_enabled'(72,'ctxModelAIM.flr',FLORA_THIS_MODULE_NAME),fllibexecute_delayed_calls([__bc,__n,__val],[__bc,__val]),true)).
?-(fllibinsrulesig(74,'ctxModelAIM.flr','_$_$_flora''descr_vars',FLORA_THIS_MODULE_NAME,48,FLORA_THIS_WORKSPACE(d^mvd)('EventScenario',flapply(detParamValue,__bc),__val,'_$ctxt'(_CallerModuleVar,74,__newcontextvar1)),','(FLORA_THIS_WORKSPACE(d^isa)(__val,'EventScenario','_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar2,74)),','(','(','(FLORA_WORKSPACE(bc,d^mvd)(__bc,notam,__n,'_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar5,74)),FLORA_WORKSPACE(bc,d^isa)(__n,'NOTAM','_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar4,74))),FLORA_WORKSPACE(bc,d^isa)(__bc,'SemNOTAMCase','_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar3,74))),','(','(FLORA_WORKSPACE(bc,d^mvd)(__n,feature,apron,'_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar6,74)),FLORA_WORKSPACE(bc,d^mvd)(__n,status,closed,'_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar7,74))),=(__val,apronClosure)))),null,'_$_$_flora''rule_enabled'(74,'ctxModelAIM.flr',FLORA_THIS_MODULE_NAME),fllibexecute_delayed_calls([__bc,__n,__val],[__bc,__val]),true)).
?-(fllibinsrulesig(76,'ctxModelAIM.flr','_$_$_flora''descr_vars',FLORA_THIS_MODULE_NAME,49,FLORA_THIS_WORKSPACE(d^mvd)('EventScenario',flapply(detParamValue,__bc),__val,'_$ctxt'(_CallerModuleVar,76,__newcontextvar1)),','(FLORA_THIS_WORKSPACE(d^isa)(__val,'EventScenario','_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar2,76)),','(','(','(FLORA_WORKSPACE(bc,d^mvd)(__bc,notam,__n,'_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar5,76)),FLORA_WORKSPACE(bc,d^isa)(__n,'NOTAM','_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar4,76))),FLORA_WORKSPACE(bc,d^isa)(__bc,'SemNOTAMCase','_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar3,76))),','(FLORA_WORKSPACE(bc,d^mvd)(__n,feature,obstruction,'_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar6,76)),=(__val,obstruction)))),null,'_$_$_flora''rule_enabled'(76,'ctxModelAIM.flr',FLORA_THIS_MODULE_NAME),fllibexecute_delayed_calls([__bc,__n,__val],[__bc,__val]),true)).
?-(fllibinsrulesig(78,'ctxModelAIM.flr','_$_$_flora''descr_vars',FLORA_THIS_MODULE_NAME,50,FLORA_THIS_WORKSPACE(d^mvd)('EventScenario',flapply(detParamValue,__bc),__val,'_$ctxt'(_CallerModuleVar,78,__newcontextvar1)),','(FLORA_THIS_WORKSPACE(d^isa)(__val,'EventScenario','_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar2,78)),','(','(','(FLORA_WORKSPACE(bc,d^mvd)(__bc,notam,__n,'_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar5,78)),FLORA_WORKSPACE(bc,d^isa)(__n,'NOTAM','_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar4,78))),FLORA_WORKSPACE(bc,d^isa)(__bc,'SemNOTAMCase','_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar3,78))),','(FLORA_WORKSPACE(bc,d^mvd)(__n,feature,heliport,'_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar6,78)),=(__val,heliport)))),null,'_$_$_flora''rule_enabled'(78,'ctxModelAIM.flr',FLORA_THIS_MODULE_NAME),fllibexecute_delayed_calls([__bc,__n,__val],[__bc,__val]),true)).
?-(fllibinsrulesig(80,'ctxModelAIM.flr','_$_$_flora''descr_vars',FLORA_THIS_MODULE_NAME,51,FLORA_THIS_WORKSPACE(d^mvd)('EventScenario',flapply(detParamValue,__bc),__val,'_$ctxt'(_CallerModuleVar,80,__newcontextvar1)),','(FLORA_THIS_WORKSPACE(d^isa)(__val,'EventScenario','_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar2,80)),','(','(','(FLORA_WORKSPACE(bc,d^mvd)(__bc,notam,__n,'_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar5,80)),FLORA_WORKSPACE(bc,d^isa)(__n,'NOTAM','_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar4,80))),FLORA_WORKSPACE(bc,d^isa)(__bc,'SemNOTAMCase','_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar3,80))),','(FLORA_WORKSPACE(bc,d^mvd)(__n,feature,waterlaneSki,'_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar6,80)),=(__val,waterlaneSki)))),null,'_$_$_flora''rule_enabled'(80,'ctxModelAIM.flr',FLORA_THIS_MODULE_NAME),fllibexecute_delayed_calls([__bc,__n,__val],[__bc,__val]),true)).
?-(fllibinsrulesig(82,'ctxModelAIM.flr','_$_$_flora''descr_vars',FLORA_THIS_MODULE_NAME,52,FLORA_THIS_WORKSPACE(d^mvd)('EventScenario',flapply(detParamValue,__bc),__val,'_$ctxt'(_CallerModuleVar,82,__newcontextvar1)),','(FLORA_THIS_WORKSPACE(d^isa)(__val,'EventScenario','_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar2,82)),','(','(','(FLORA_WORKSPACE(bc,d^mvd)(__bc,notam,__n,'_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar5,82)),FLORA_WORKSPACE(bc,d^isa)(__n,'NOTAM','_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar4,82))),FLORA_WORKSPACE(bc,d^isa)(__bc,'SemNOTAMCase','_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar3,82))),','(FLORA_WORKSPACE(bc,d^mvd)(__n,feature,aerodromeBeacon,'_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar6,82)),=(__val,aerodromeBeaconStatus)))),null,'_$_$_flora''rule_enabled'(82,'ctxModelAIM.flr',FLORA_THIS_MODULE_NAME),fllibexecute_delayed_calls([__bc,__n,__val],[__bc,__val]),true)).
?-(fllibinsrulesig(84,'ctxModelAIM.flr','_$_$_flora''descr_vars',FLORA_THIS_MODULE_NAME,53,FLORA_THIS_WORKSPACE(d^mvd)('EventScenario',flapply(detParamValue,__bc),__val,'_$ctxt'(_CallerModuleVar,84,__newcontextvar1)),','(FLORA_THIS_WORKSPACE(d^isa)(__val,'EventScenario','_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar2,84)),','(','(','(FLORA_WORKSPACE(bc,d^mvd)(__bc,notam,__n,'_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar5,84)),FLORA_WORKSPACE(bc,d^isa)(__n,'NOTAM','_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar4,84))),FLORA_WORKSPACE(bc,d^isa)(__bc,'SemNOTAMCase','_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar3,84))),','(FLORA_WORKSPACE(bc,d^mvd)(__n,feature,aerodromeILS,'_$ctxt'(FLORA_THIS_MODULE_NAME,__newcontextvar6,84)),=(__val,aerodromeEquipment)))),null,'_$_$_flora''rule_enabled'(84,'ctxModelAIM.flr',FLORA_THIS_MODULE_NAME),fllibexecute_delayed_calls([__bc,__n,__val],[__bc,__val]),true)).


%%%%%%%%%%%%%%%%%%%%%%%%% Signatures for latent queries %%%%%%%%%%%%%%%%%%%%%%%%
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%




%%%%%%%%%%%%%%%%%%%%%%% Queries found in the source file %%%%%%%%%%%%%%%%%%%%%%%
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%


 
#if !defined(FLORA_FLS2_FILENAME)
#if !defined(FLORA_LOADDYN_DATA)
#define FLORA_LOADDYN_DATA
#endif
#mode save
#mode nocomment "%"
#define FLORA_FLS2_FILENAME  'ctxModelAIM.fls2'
#mode restore
?-(:(flrutils,flora_loaddyn_data(FLORA_FLS2_FILENAME,FLORA_THIS_MODULE_NAME,'fls2'))).
#else
#if !defined(FLORA_READ_CANONICAL_AND_INSERT)
#define FLORA_READ_CANONICAL_AND_INSERT
#endif
?-(:(flrutils,flora_read_symbols_canonical_and_insert(FLORA_FLS2_FILENAME,FLORA_THIS_FLS_STORAGE,_SymbolErrNum))).
#endif

/************************************************************************
  file: headerinc/flrtrailer_inc.flh

  Author(s): Michael Kifer

  This file is automatically included by the Flora-2 compiler.
************************************************************************/

#include "flrtrailer.flh"

/***********************************************************************/

/************************************************************************
  file: headerinc/flrpreddef_inc.flh

  Author(s): Chang Zhao

  This file is automatically included by the Flora-2 compiler.
************************************************************************/

:-(compiler_options([xpp_on])).

#include "flrpreddef.flh"

/***********************************************************************/

