package app;

import app.Model.AtomicOperation;
import app.Model.ComposedOperation;
import app.Model.Message;
import app.Model.Operations.AddContext;
import app.Model.Operations.AddParameterValue;
import app.Model.SendMessage;
import app.Model.Operations.SplitContext;
import app.Model.User;
import app.Repository.*;
import dke.pr.cli.CBRInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.Date;

@Component
public class Startup implements ApplicationListener<ApplicationReadyEvent> {
    @Value("${spring.jpa.hibernate.ddl-auto}")
    private String ddlStrategy;

    public static String flora2Path;

    @Value("${flora2Path}")
    public void setFlora2Path(String flora2Path) {
        this.flora2Path = flora2Path;
    }

    private final ContextDBRepository contextDBRepository;
    private final UserRepository userRepository;
    private final OperationRepository operationRepository;
    private final MessageRepository messageRepository;

    @Autowired
    public Startup(ContextDBRepository contextDBRepository, UserRepository userRepository, OperationRepository operationRepository, MessageRepository messageRepository) {
        this.contextDBRepository = contextDBRepository;
        this.userRepository = userRepository;
        this.operationRepository = operationRepository;
        this.messageRepository = messageRepository;
    }

    /**
     * If the ddlStrategy is set to create the CBRM files are dropped and initiated with those of CBRM-template.
     * In this case the right Paths for the Context Files are set within the ctxModelAIM.flr.
     *
     * This event is executed as late as conceivably possible to indicate that
     * the application is ready to service requests.
     */
    @Override
    public void onApplicationEvent(final ApplicationReadyEvent event) {
        if (ddlStrategy.equals("create") || ddlStrategy.equals("create-drop")) {
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
                Flora2Repository fl = new Flora2Repository();
                fl.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
