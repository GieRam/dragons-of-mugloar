import dragons.clients.GameClient;
import dragons.data.Game;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class Application {

    public static void main(String[] args) throws Exception {
        AbstractApplicationContext context = new AnnotationConfigApplicationContext();
        context.refresh();
        Application application = context.getBean(Application.class);
        application.start(args);
    }

    @Autowired
    private GameClient gameClient;

    private void start(String[] args) throws IOException {
        Game game = gameClient.getGame();
        System.out.println(game.getGameId());
    }
}
