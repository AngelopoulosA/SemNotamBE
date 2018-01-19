package app.Controller;

import app.Model.Notam;
import app.Model.SemNotam;
import app.Repository.Flora2Repository;
import dke.pr.cli.CBRInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(path="/query")
@CrossOrigin
public class QueryController {

	@Autowired
	public QueryController() {
    };

	@GetMapping(path="")
	public @ResponseBody
	List<SemNotam> querySemNotams () {
        try (Flora2Repository fl = new Flora2Repository()) {

			String iSpec = "iSpec1";
			int i = 0;

			List<SemNotam> semNotams = new LinkedList<>();
            List<String> notams = fl.getNOTAMS();
            for (String notamID : notams) {
                String bc = "bc_"+i;
                fl.newBusinessCase("${"+bc+":SemNOTAMCase[interestSpec->"+iSpec+",notam->"+notamID+"]@bc}");
                fl.detRelevantCtxs(bc);
                fl.detCaseSpecificCtx(bc, "m");
                List<String> results = fl.readTargetModule("m");

                List<String[]> notamInfos = fl.getNOTAMInfo(notamID);
                Notam notam = new Notam(notamID);
                for (String[] notamInfo : notamInfos) {
                    notam.getProperties().put(notamInfo[0], notamInfo[1]);
                }

                SemNotam semNotam = new SemNotam(notam, results);
                semNotams.add(semNotam);

                i++;
            }

			return semNotams;
		} catch (IOException e) {
			return null;
		}
	}

	@GetMapping(path="ispec")
	public @ResponseBody
	Map<String, String> getIspecDetails () {
        try (Flora2Repository fl = new Flora2Repository()) {

			String iSpec = "iSpec1";
			Map<String, String> iSpecDetails = new HashMap<>();
			List<String[]> iSpecInfos = fl.getISpecInfo(iSpec);
			for (String[] iSpecInfo : iSpecInfos) {
				iSpecDetails.put(iSpecInfo[0], iSpecInfo[1]);
			}
			return iSpecDetails;
		} catch (IOException e) {
			return null;
		}
	}

	@PostMapping(path="ispec")
	public @ResponseBody
	Map<String, String> updateIspecDetails (@RequestBody Map<String, String> iSpecDetails) {
        try (Flora2Repository fl = new Flora2Repository()) {

			String iSpec = "iSpec1";
			fl.delInterestSpec(iSpec);
			fl.addInterestSpec("iSpec1:InterestSpec[interest->"+iSpecDetails.get("interest")+",flightPhase->"+iSpecDetails.get("flightPhase")+"].");

			return getIspecDetails();
		} catch (IOException e) {
			return null;
		}
	}
}
