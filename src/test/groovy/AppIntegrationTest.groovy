import groovyx.net.http.HttpResponseException
import groovyx.net.http.RESTClient
import spock.lang.Specification

import static groovyx.net.http.ContentType.JSON


/**
 * Created by nkakarla on 4/19/17.
 */

public class AppIntegrationTest extends Specification {

    RESTClient mockClient = new RESTClient("http://localhost:8092")
    RESTClient appClient = new RESTClient("http://localhost:8082")


    def setup(){

    }

    def cleanup(){

        mockClient.post(path: '__admin/mappings/reset',
                        body: '{}',
                        requestContentType : JSON)
    }


    def 'Test greeting api when weather api is down (500)'() {

        given:

        def city = 'Minneapolis'
        and:
        def json = this.getClass().getResource('_get_ext_api1_500.json').text
        def mockResponse = mockClient.post(path: '/__admin/mappings',
                body: json,
                requestContentType : JSON )

        when:
        def appResponse = appClient.get(path: '/tickers/city/'+ city)

        then:
        def e = thrown(HttpResponseException)
        and:
        e.statusCode == 500
    }


    def 'Test greeting api when weather api has latency of 5 seconds'() {
        given:

        def city = 'Minneapolis'
        and:
        def json = this.getClass().getResource('_get_ext_api1_200_withdelay_5000ms.json').text
        def mockResponse = mockClient.post(path: '/__admin/mappings',
                body: json,
                requestContentType : JSON )

        when:
        def appResponse = appClient.get(path: '/tickers/city/'+ city)

        then:

        appResponse.status == 200

    }


    def 'Test greeting api when weather api returns 400'() {

        given:

        def city = 'Minneapolis'
        and:
        def json = this.getClass().getResource('_get_ext_api1_400.json').text
        def mockResponse = mockClient.post(path: '/__admin/mappings',
                body: json,
                requestContentType : JSON )

        when:
        def appResponse = appClient.get(path: '/tickers/city/'+ city)

        then:
        def e = thrown(HttpResponseException)
        and:
        e.statusCode == 400
    }

}
