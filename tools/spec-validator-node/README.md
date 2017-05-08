## spec-validator-node

**This tool does not operate to expectations**

A Swagger validator in Node. Uses the [Sway](https://www.npmjs.com/package/swagger-validator) module which is how editor.swagger.io actually does its validation.

However it does not generate the same error results as editor.swagger.io. Sigh.
Make sure you are running a recent version of Node, e.g. 6.2.1 and that you have `npm install`

How to use:

```
node validate.js
```

**Assumes api.json is in the same directory as the validate.js script**
