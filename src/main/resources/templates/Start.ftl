package ${basePackage};

import de.fhws.fiw.fds.sutton.server.AbstractDatabaseInstaller;
import de.fhws.fiw.fds.sutton.server.AbstractStart;

public class Start extends AbstractStart {

    public final static String CONTEXT_PATH = "${baseContextPath}";

    public Start() {
        super(CONTEXT_PATH);
    }

    public static void main(String[] args) throws Exception {
        new Start().startTomcat();
    }

    @Override
    protected AbstractDatabaseInstaller getInstaller() {
        return new DatabaseInstaller();
    }
}
