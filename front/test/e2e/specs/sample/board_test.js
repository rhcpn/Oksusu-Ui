// For authoring Nightwatch tests, see
// http://nightwatchjs.org/guide#usage

module.exports = {

  before : function (browser) {
    //browser.resizeWindow(1200, 1000);
  },

  'board tests': function (browser) {
    // automatically uses dev Server port from /config.index.js
    // default: http://localhost:8080
    // see nightwatch.conf.js
    const devServer = browser.globals.devServerURL;
    const waitTime = browser.globals.waitTime;

    browser
      .url(devServer)
      .waitForElementVisible('body', waitTime)
      .frame(null)
      .useXpath()     // every selector now must be XPath
      .click("//*[contains(text(), 'Board')]")
      //.click('#root.container ul li:nth-child(1)')
      .pause(waitTime)
      .click("//*[contains(text(), 'VueBoard')]")
      .useCss()
      .pause(waitTime)
      .frame('storybook-preview-iframe')
      .waitForElementVisible('#name', waitTime)
      .setValue('#name', 'rikko')
      .setValue('#birth', '1987-03-14')
      .setValue('#age', '53')
      .setValue('#email', 'dd@gmail.com')
      .setValue('#url', 'http://naver.com')
      .setValue('#id', 'riko4342')
      .click("#jobA")
      .click("#jobC")
      .click("#Female")
      .click('#location option[value=B]')
      .click(".btn-primary")
      .pause(waitTime)
      .useCss()
      .assert.visible('#recordlist')
      .click('#recordlist')
      .pause(waitTime)
      .click("#recordlist ul li:nth-child(1) > a")  // css
      .pause(waitTime)
  },
  after : function (browser) {
    browser.end();
  }
};
