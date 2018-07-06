// For authoring Nightwatch tests, see
// http://nightwatchjs.org/guide#usage

module.exports = {

  before : function (browser) {
    //browser.resizeWindow(1200, 1000);
  },

  'button tests': function (browser) {
    // automatically uses dev Server port from /config.index.js
    // default: http://localhost:8080
    // see nightwatch.conf.js
    const devServer = browser.globals.devServerURL;
    const waitTime = browser.globals.waitTime;

    browser
      .url(devServer)
      .waitForElementVisible('body', waitTime)
      .assert.elementPresent('body')
      .frame('storybook-preview-iframe')
      .assert.title('Storybook')
      .waitForElementVisible('body', waitTime)
      .assert.visible('.main h1')
      .assert.containsText('.main h1', 'Welcome to STORYBOOK')
      .pause(waitTime)
      .frame(null)
      .useXpath()     // every selector now must be XPath
      .click("//*[contains(text(), 'Button')]")
      .useCss()      // we're back to CSS now
      .pause(waitTime)
      .useXpath()     // every selector now must be XPath
      .click("//*[contains(text(), 'with text')]")
      .pause(waitTime)
      .frame('storybook-preview-iframe')
      .useXpath()     // every selector now must be XPath
      .click("//*[contains(text(), 'Hello Button')]")
      .useCss()      // we're back to CSS now
      .pause(waitTime)
  },
  after : function (browser) {
    browser.end();
  }
};
