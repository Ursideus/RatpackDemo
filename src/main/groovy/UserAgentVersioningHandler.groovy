import ratpack.handling.Context
import ratpack.handling.Handler
//import ratpack.jackson.JacksonModule
//import ratpack.registry.Registries

//import static ratpack.groovy.Groovy.ratpack
//import static ratpack.jackson.Jackson.json

/* ----------------------------------------------------------- */
// Standalone Handler w/ user agent evaluation.
/* ----------------------------------------------------------- */

class UserAgentVersioningHandler implements Handler {
  private static final String ERROR_MSG = "Unsupported User Agent"

  enum ClientVersion {
    V1("Microservice Client v1.0"),
    V2("Microservice Client v2.0"),
    V3("Microservice Client v3.0")

    String versionString

    ClientVersion(String versionString) {
      this.versionString = versionString
    }

    static ClientVersion fromString(String versionString) {
      for (val in values()) {
        if (val.versionString == versionString) {
          return val
        }
      }
      null
    }
  }

  @Override
  void handle(Context context) {
    def userAgent = context.request.headers."User-Agent" // (1)
    def clientVersion = ClientVersion.fromString(userAgent)
    if (!clientVersion) {
      renderError(context)
    } else {
      context.next(Registries.just(ClientVersion, clientVersion)) // (2)
    }
  }

  private static void renderError(Context context) {
    context.response.status(400)
    context.byContent { spec -> spec
      .json({
        context.render(json([error: true, message: ERROR_MSG]))
      } as Handler)
      .html({
        context.render("<h1>400 Bad Request</h1><br/><div>${ERROR_MSG}</div>")
      } as Handler)
      .noMatch({
        context.render(ERROR_MSG)
      } as Handler)
    }
  }
}
