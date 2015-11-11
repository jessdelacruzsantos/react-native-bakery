# SDK source

These directories contain the source for the Connect SDK in various languages.
This README explains the process getting started using each SDK in this repo.

## Using Ruby

`cd` into the `rubygem` directory and build yourself a local version of the gem
with the following command:

    gem build square_connect.gemspec

Then install the generated gem with the following command:

    gem install square_connect-0.0.0.gem

Now you can create a local ruby app that just `require`s `square-connect`.


## Using PHP

The PHP SDK uses Composer to fetch its dependencies (right now just the PHP
protobuf library). To install and run composer:

1. Follow the OSX installation instructions [here](https://getcomposer.org/doc/00-intro.md).
   (run the command from `src/php`).

2. Run the following from `src/php`:

        php composer.phar install

Now the SDK has everything it needs to run. To include the SDK in your PHP project,
just `require` the path to `SquareConnect.php`.



## Using Java and Python

These are still in development and can't be used yet. Sorry!
