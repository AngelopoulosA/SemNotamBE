package app;

import dke.pr.cli.CBRInterface;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

@Component
public class Startup implements ApplicationListener<ApplicationReadyEvent> {
    @Value("${spring.jpa.hibernate.ddl-auto}")
    private String ddlStrategy;

    public static String flora2Path;

    @Value("${flora2Path}")
    public void setSvnUrl(String flora2Path) {
        this.flora2Path = flora2Path;
    }

        /**
         * This event is executed as late as conceivably possible to indicate that
         * the application is ready to service requests.
         */
        @Override
        public void onApplicationEvent(final ApplicationReadyEvent event) {

            if (ddlStrategy.equals("create")) {
                try {
                    File cbrm = new File("CBRM");
                    File cbrmCurrent = new File("CBRM/current");
                    File cbrmTemplate = new File("CBRM-template");
                    if(cbrm.exists()) {
                        FileUtils.forceDelete(cbrm);
                    }
                    FileUtils.forceMkdir(cbrm);
                    FileUtils.forceMkdir(cbrmCurrent);
                    FileUtils.copyDirectory(cbrmTemplate, cbrmCurrent);
                    File ctxModelAIM = new File("CBRM/current/ctxModelAIM.flr");
                    String path = cbrmCurrent.getAbsolutePath().replace('\\', '/');
                    CBRInterface.replaceRegExPatternFromFile(ctxModelAIM, "<PATH>", path);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
}
