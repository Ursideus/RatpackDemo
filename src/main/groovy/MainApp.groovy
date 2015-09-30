
/*
Explicit main class, instead of src/ratpack/ratpack.groovy
*/

import ratpack.groovy.Groovy
import ratpack.server.RatpackServer
import ratpack.server.ServerConfig

class MainJava {

  public static void main(String[] args) {
    RatpackServer.start { spec -> spec
      .serverConfig(ServerConfig.noBaseDir())
      .handlers(Groovy.chain {
        get {
          render "Hello World!"
        }
      })
    }
  }
}

// public class Main {
//   public static void main(String[] args) throws Exception {
//     RatpackServer.start(spec -> spec
//         .serverConfig(ServerConfig.noBaseDir())
//         .handlers(chain -> chain
//           .handler(c -> c
//           	.byMethod(spec -> spec
//           		.get(ctx -> ctx.render("Hello World!"))
//           		.post(ctx -> {
//           			Form form = ctx.parse(Form);
//           			ctx.render("Hello " + form.get("data"));
//           		})
//           	)
//           )
//         )
//     );
//   }
// }
