package app.Repository;

import app.Startup;
import dke.pr.cli.CBRInterface;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

public class Flora2Repository extends CBRInterface implements AutoCloseable  {

    public Flora2Repository() throws IOException {
        super();
        initiate(Startup.flora2Path,"CBRM/current/ctxModelAIM.flr", "CBRM/current/bc.flr", "AIMCtx", "SemNOTAMCase");
        setDebug(false);
    }

    public String getCtxFileName(String context) {
        File cbrmCurrent = new File("CBRM/current");
        String path = cbrmCurrent.getAbsolutePath().replace('\\', '/');
        return path+"/"+context+".flr";
    }


}
