/*
 * This Spock specification was generated by the Gradle 'init' task.
 */

import com.rilussion.alpha.Application
import spock.lang.Specification

class AppTest extends Specification {
    def "1_application has a greeting"() {
        setup:
        def app = new Application()
        and:
            Thread.sleep 5100

        when:
        def result = app

        then:
        result != null
    }

    def "1_application has a greeting1"() {
        setup:
        def app = new Application()
        and:
            Thread.sleep 6100

        when:
        def result = app

        then:
        result != null
    }

    def "1_application has a greeting2"() {
        setup:
        def app = new Application()
        and:
        Thread.sleep 7100

        when:
        def result = app

        then:
        result != null
    }

    def "2_application has a greeting3"() {
        setup:
        def app = new Application()
        and:
        Thread.sleep 5100

        when:
        def result = app

        then:
        result != null
    }

    def "2_application has a greeting4"() {
        setup:
        def app = new Application()
        and:
        Thread.sleep 6100

        when:
        def result = app

        then:
        result != null
    }

    def "2_application has a greeting5"() {
        setup:
        def app = new Application()
        and:
        Thread.sleep 7100

        when:
        def result = app

        then:
        result != null
    }
}
