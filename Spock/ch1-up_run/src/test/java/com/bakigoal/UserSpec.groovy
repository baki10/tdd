package com.bakigoal

import spock.lang.Specification

class UserSpec extends Specification {

    def user
    def other

    def setupSpec() {
        println "> setupSpec"
    }

    def cleanupSpec() {
        println "> cleanupSpec"
    }

    def setup() {
        println "  > setup"
        user = new User("kirk")
        other = new User("spock")
    }

    def cleanup() {
        println "  > cleanup"
    }

    def "a user can follow another user"() {
        println "----- feature method 1"
        when:
        user.follow(other)
        then:
        user.following.size() == 1
        and:
        user.following.contains(other)
    }

    def "a user reports if they are following someone"() {
        println "----- feature method 2"
        expect:
        !user.follows(other)
        when:
        user.follow(other)
        then:
        user.follows(other)
    }
}
