import ratpack.handling.Context
import ratpack.handling.Handler
//import ratpack.jackson.JacksonModule
//import ratpack.registry.Registries
//import ratpack.config.ConfigData
//import static ratpack.groovy.Groovy.groovyMarkupTemplate
//import ratpack.groovy.template.MarkupTemplateModule
//import ratpack.form.Form
import static ratpack.groovy.Groovy.ratpack
import static ratpack.jackson.Jackson.json

/* ----------------------------------------------------------- */
// Standalone Handler w/ user agent evaluation.
/* ----------------------------------------------------------- */

ratpack {
  // bindings {
  //    add(new UserAgentVersioningHandler_1())
  //  }
  handlers {
    handler(new UserAgentVersioningHandler()) // (3)

    handler("api") { UserAgentVersioningHandler.ClientVersion clientVersion -> // (4)
      if (clientVersion == UserAgentVersioningHandler.ClientVersion.V1) {
        render "V1 Model"
      } else if (clientVersion == UserAgentVersioningHandler.ClientVersion.V2) {
        render "V2 Model"
      } else {             // it must be V3 at this point, since the versioning
        render "V3 Model"  // handler has figured out the request qualifies
      }
    }
  }
}

/* ----------------------------------------------------------- */
// Standalone Handler w/ argumant evaluation.
/* ----------------------------------------------------------- */
// ratpack {
//   bindings {
//     add(new DefaultRouteHandler("Hello World!"))
//   }
//   handlers {
//     get(DefaultRouteHandler)
//     get(":name", DefaultRouteHandler)
//   }
// }

/* ----------------------------------------------------------- */
// Standalone Handler.
/* ----------------------------------------------------------- */
// ratpack {
//   bindings {
//     add(new DefaultRouteHandler("Hello World!"))
//   }
//   handlers {
//     get(DefaultRouteHandler)
//   }
// }

/* ----------------------------------------------------------- */
// Handlers with Mime Type Negotiation.
/* ----------------------------------------------------------- */
// ratpack {
//   handlers {
//     prefix("api") {
//       handler {
//         byMethod {
//           get {
//             String content = "This is my content"
//             byContent {
//               json {
//                 render "{\"content\": \"$content\"}"
//               }
//               xml {
//                 render "<content>$content</content>"
//               }
//               html {
//                 render "<!DOCTYPE html><html><body>$content</body></html>"
//               }
//               plainText {
//                 render content
//               }
//               // add custom type handler
//               type("application/hal+json") {
//                 render content + ' with custom mime type'
//               }
//               noMatch {
//                 render "I have no clue what you wanted from me."
//               }
//             }
//           }
//           post {
//             Form form = parse(Form)
//             String data = form.data
//             byContent {
//               json {
//                 render "{\"message\": \"Received $data\"}"
//               }
//               xml {
//                 render "<message>Received $data</message>"
//               }
//               html {
//                 render "<!DOCTYPE html><html><body>Thank you for submitting $data</body></html>"
//               }
//               plainText {
//                 render data
//               }
//               noMatch {
//                 render "I have no clue what you wanted from me."
//               }
//             }
//           }
//         }
//       }
//     }
//   }
// }

// -> Usage
// HTTP GET: curl -H "Accept: application/json" localhost:5050/api
// HTTP GET: curl -H "Accept: application/xml" localhost:5050/api
// HTTP GET: curl -H "Accept: text/html" localhost:5050/api
// HTTP GET: curl -H "Accept: text/plain" localhost:5050/api
// HTTP GET: curl -H "Accept: uCantMatchThis" localhost:5050/api
// HTTP POST: curl -d "data=foo" -H "Accept: application/json" localhost:5050/api
// HTTP POST: curl -d "data=foo" -H "Accept: application/xml" localhost:5050/api
// HTTP POST: curl -d "data=foo" -H "Accept: text/html" localhost:5050/api
// HTTP POST: curl -d "data=foo" -H "Accept: text/plain" localhost:5050/api
// HTTP POST: curl -d "data=foo" -H "Accept: uCantMatchThis" localhost:5050/api

/* ----------------------------------------------------------- */
// Handlers for specific prefix.
/* ----------------------------------------------------------- */
// ratpack {
//   handlers {
// 	   prefix("api") {
//       handler {
//         byMethod {
//           get {
//             render "Hello World!"
//           }
//           post {
//             Form form = parse(Form)
//             form.get
//             render "Hello ${form.data}"
//           }
//         }
//       }
//     }
//   }
// }

/* ----------------------------------------------------------- */
// Handling different HTTP verbs for the same route.
/* ----------------------------------------------------------- */
// ratpack {
//   handlers { chain ->
//     chain.handler { contx ->
//       contx.byMethod { spec ->
//         spec.get { ctx ->
//           ctx.render "this is root get handler!"
//         }
//         // post {
//         //   Form form = parse(Form)
//         //   render "Hello ${form.data}"
//         // }
//       }
//     }
//   }
// }

// -> Java equivalent:
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

/* ----------------------------------------------------------- */
//  Handlers with URI prefix
/* ----------------------------------------------------------- */
// ratpack {
//   handlers {
//     prefix("api") {
//       get {
//         render "Hello World! from get()"
//       }
//       post {
//         Form form = parse(Form)
//         render 'OK'
//       }
//     }
//   }
// }

/* ----------------------------------------------------------- */
//  Bind with model class
/* ----------------------------------------------------------- */
// ratpack {
//     bindings {
//         module JacksonModule
//     }
//     handlers {
//         get {
//             render 'Hello Visitor!'
//         }
//         get('person') {
//             render json(new Person(id: 1, name: 'John Doe', age: 33))
//         }
//     }
// }
//
// class Person {
//    Long id
//    String name
//    int age
// }
/* ----------------------------------------------------------- */
/*ratpack {
    handlers {
        get('users/:id') { //ex: /users/xyz123
            render context.pathTokens['id']
        }
        get('users') { //ex: /users/
            render 'no path token'
        }
        get('repeatString/:string/:times:[\\d]+') {
            //ex: /repeatString/X/10  would send back 10 Xs
            render context.pathTokens['string'] * context.pathTokens['times'].toInteger()
        }
    }
}*/

/*ratpack {
  handlers {
    get('getmapping') {
        render 'some data'
    }
    post('postmapping') {
        println request.body.text
        render 'some output'
    }
  }
}
*/
/*ratpack {
  handlers {
    handler {
        println 'some stuff done here'
        next()
    }
    handler {
      render 'some output'
    }
  }
}*/

/*ratpack {
    handlers {
        get("hello") {
            render "Hello, World!"
        }

        get("goodbye") {
            render "Goodbye, World!"
        }
    }
}*/

/*ratpack {
  bindings {
    module MarkupTemplateModule
    // Define config file/Env settings path
    ConfigData configData = ConfigData.of()
            .yaml(ClassLoader.getSystemResource('application.yml'))
            .props(ClassLoader.getSystemResource('application.properties') as URL)
            .sysProps()
            .env()
            .build()
    bindInstance(ConfigData, configData) //this loads this instance into Guice
  }

  handlers {
    // Read config file/Env settings
    handler { ConfigData configData ->
      println configData.get("/property1", String)
      println configData.get("/property2/subproperty2", String)
      println configData.get("/javasysprop", String)
      println configData.get("/MYPROP", String)
      render "done"
    }
    get {
      render groovyMarkupTemplate("index.gtpl", title: "My Ratpack App")
    }

    files { it.dir('public') }
    //files { dir "public" }
    // fileSystem('public') { f ->
    //         f.files()
    //     }

  }
}
*/
