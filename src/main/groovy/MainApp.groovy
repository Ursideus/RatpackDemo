
/*
Explicit main class, instead of src/ratpack/ratpack.groovy
*/
import ratpack.groovy.Groovy
import ratpack.server.RatpackServer
import ratpack.server.ServerConfig

class MainGroovy {

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
