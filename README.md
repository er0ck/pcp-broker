## pcp-broker

A message broker for the PCP protocol

## Installing

To use this service in your trapperkeeper application, simply add this
project as a dependency in your leiningen project file:

[![Clojars Project](http://clojars.org/puppetlabs/pcp-broker/latest-version.svg)](http://clojars.org/puppetlabs/pcp-broker)

And then see [these notes on configuring](doc/configuration.md)

## Running the server

For development purposes you can run a broker out of a checkout using
the *insecure* certificates provided in test-resources/ with the
following command:

    lein tk

## Documentation

Look [here](doc/).

## Support

We use the [Puppet Communications Protocol project on JIRA](https://tickets.puppetlabs.com/browse/PCP)
with the component `pcp-broker` for tickets on `puppetlabs/pcp-broker`.
