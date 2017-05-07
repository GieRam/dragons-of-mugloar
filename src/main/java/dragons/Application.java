package dragons;

import dragons.engine.GameEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class Application {

    public static void main(String[] args) throws Exception {
        AnnotationConfigApplicationContext context = setupApplicationContext();

        Application application = context.getBean(Application.class);
        application.start(args);
    }

    private static AnnotationConfigApplicationContext setupApplicationContext() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.scan("dragons");
        context.refresh();
        return context;
    }

    @Autowired
    private GameEngine gameEngine;

    private void start(String[] args) throws Exception {
        gameEngine.playGame(100);
    }
}
