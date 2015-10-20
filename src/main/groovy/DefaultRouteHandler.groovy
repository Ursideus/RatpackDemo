import static ratpack.groovy.Groovy.ratpack
import ratpack.handling.Handler
import ratpack.handling.Context

/* ----------------------------------------------------------- */
// Standalone Handler w/ reusability.
/* ----------------------------------------------------------- */

class DefaultRouteHandler implements Handler {
  private final String defaultMessage

  DefaultRouteHandler(String message) {
    this.defaultMessage = message
  }

  @Override
  void handle(Context context) {
    if (context.pathTokens.containsKey("name")) {
      context.render "Hello, ${context.pathTokens.name}!"
    } else {
      context.render defaultMessage
    }
  }
}

/* ----------------------------------------------------------- */
// Standalone Handler.
/* ----------------------------------------------------------- */

// class DefaultRouteHandler implements Handler {
//   private final String message
//
//   DefaultRouteHandler(String message) {
//     this.message = message
//   }
//
//   @Override
//   void handle(Context context) {
//     context.render message
//   }
// }
