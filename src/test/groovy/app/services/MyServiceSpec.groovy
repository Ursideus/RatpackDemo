package app.services

import ratpack.groovy.test.GroovyRatpackMainApplicationUnderTest
import spock.lang.Specification

class MyServiceSpec extends Specification {

  void "default handler should render Hello World"() {
  	setup:
  	"Set up the service for testing"
  	def service = new MyService()

    when:
    "Perform the service call"
    def result = service.doServiceCall()

    then:
    "Ensure that the service call returned the proper result"
    //response == "service was called"
    result == "service was called"

    cleanup:
    "Shutdown the service when this feature is complete"
    service.shutdown()
  }
}
