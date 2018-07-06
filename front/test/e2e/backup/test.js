// For authoring Nightwatch tests, see
// http://nightwatchjs.org/guide#usage

this.demoTest = function(client) {
  this.getLog('browser', function(logEntriesArray) {
    console.log('Log length: ' + logEntriesArray.length);
    logEntriesArray.forEach(function(log) {
      console.log('[' + log.level + '] ' + log.timestamp + ' : ' + log.message);
    });
  });
};

module.exports = {

  before : function (browser) {
    //browser.resizeWindow(1200, 1000);
  },

  'default e2e tests': function (browser) {
    // automatically uses dev Server port from /config.index.js
    // default: http://localhost:8080
    // see nightwatch.conf.js
    const devServer = browser.globals.devServerURL;

    browser
      .url(devServer)
      .waitForElementVisible('body', 2000)
      .assert.elementPresent('body')
      .frame('storybook-preview-iframe')
      .assert.title('Storybook')
      .waitForElementVisible('body', 2000)
      .assert.visible('.main h1')
      .assert.containsText('.main h1', 'Welcome to STORYBOOK')
      .pause(2000)
      .frame(null)
      .useXpath()     // every selector now must be XPath
      .click("//*[contains(text(), 'Button')]")
      .pause(2000)
      .click("//*[contains(text(), 'with text')]")
      .pause(2000)
      .frame('storybook-preview-iframe')
      .click("//*[contains(text(), 'Hello Button')]")
      .useCss()      // we're back to CSS now
      .pause(6000)
  },
  after : function (browser) {
    browser.end();
  }
};


/*module.exports = {
  'Demo test Google' : function (browser) {
    browser
      .url('http://www.google.com')
      .waitForElementVisible('body', 1000)
      .end();
  }
};*/
