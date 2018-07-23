// For authoring Nightwatch tests, see
// http://nightwatchjs.org/guide#usage

module.exports = {

  before: function (browser) {
    // browser.resizeWindow(1200, 1000);
  },

  'list tests': function (browser) {
    // automatically uses dev Server port from /config.index.js
    // default: http://localhost:8080
    // see nightwatch.conf.js
    const devServer = browser.globals.devServerURL
    const waitTime = browser.globals.waitTime

    browser
      .url(devServer)
      .waitForElementVisible('body', waitTime)
      .frame(null)
      .useXpath() // every selector now must be XPath
      .click("//*[contains(text(), 'List')]")
      // .click('#root.container ul li:nth-child(1)')
      .pause(waitTime)
      .click("//*[contains(text(), 'ListTest')]")
      // .useCss()
      .pause(waitTime)
      .frame('storybook-preview-iframe')
      // .click("//div[@id='recordlist']")
      // .click("//div[@id='recordlist']//a[1]")   // xPath
      .useCss()
      .click('#recordlist ul li:nth-child(3) > a') // css
      .pause(waitTime)
  },
  after: function (browser) {
    browser.end()
  }
}
