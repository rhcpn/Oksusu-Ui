// For authoring Nightwatch tests, see
// http://nightwatchjs.org/guide#usage

this.demoTest = function (client) {
  this.getLog('browser', function (logEntriesArray) {
    console.log('Log length: ' + logEntriesArray.length)
    logEntriesArray.forEach(function (log) {
      console.log('[' + log.level + '] ' + log.timestamp + ' : ' + log.message)
    })
  })
}

module.exports = {

  before: function (browser) {
    browser.resizeWindow(1900, 1000)
  },

  'prototype detail filter': function (browser) {
    // automatically uses dev Server port from /config.index.js
    // default: http://localhost:8080
    // see nightwatch.conf.js
    const devServer = browser.globals.devServerURL
    const waitTime = 2000

    browser
      .url(devServer)
      .pause(waitTime)
      .useXpath()
      .waitForElementVisible('//input[@name="login"]', waitTime)
      .setValue('//input[@name="login"]', 'admin')
      .waitForElementVisible('//input[@name="login"]', waitTime)
      .setValue('//input[@name="password"]', 'admin')
      .click('//button[@class="login btn"]')
      .pause(waitTime)

      .click('//div[@class="list"]//div/child::div[1]//div[@class="item"]')
      .pause(waitTime)
      .click('//div[@class="list"]//div/child::div[2]//div[@class="item"]')
      .pause(waitTime)
      .click('//div[@class="list"]//div/child::div[3]//div[@class="item"]')
      .pause(waitTime)
      .click('//div[@class="list"]//div/child::div[4]//div[@class="expanded closed"]')
      .pause(waitTime)
      .click('//div[@class="list"]//div/child::div[5]//div[@class="item"]')
      .pause(waitTime)
      .click('//div[@class="list"]//div/child::div[6]//div[@class="item"]')
      .pause(waitTime)
      .click('//div[@class="panel-body"]//div[@class="tabs sub-tabs"]//div[@class="tabs__container"]/child::div[4]')
      .pause(waitTime)
      .click('//div[@class="panel-body"]//div[@class="tabs sub-tabs"]//div[@class="tabs__container"]/child::div[5]')
      .pause(waitTime)
      .click('//div[@class="panel-body"]//div[@class="tabs sub-tabs"]//div[@class="tabs__container"]/child::div[6]')
      .pause(waitTime)

      /* .click('//div[@class="panel-body"]//div[@class="tabs sub-tabs"]//!*[@class="tabs__div"]//following-sibling::div[4]')
      .pause(waitTime)
      .click('//div[@class="panel-body"]//div[@class="tabs sub-tabs"]//!*[@class="tabs__div"]//following-sibling::div[5]')
      .pause(waitTime)
      .click('//div[@class="panel-body"]//div[@class="tabs sub-tabs"]//!*[@class="tabs__div"]//following-sibling::div[6]')
      .pause(waitTime)
      .click('//div[@class="panel-body"]//div[@class="tabs sub-tabs"]//!*[@class="tabs__div"]//following-sibling::div[7]') */

      .click('//div[@class="list"]//div/child::div[7]//div[@class="expanded closed"]')
      .pause(waitTime)

      .click('//div[@class="list"]//div/child::div[8]//div[@class="item"]')
      .click('//*[@id="app"]/div[2]/div/div/div[2]/div[2]/div/div[2]/div[2]/div/div/div/div[2]/a')
      .pause(waitTime)
      .click('//*[@id="borderLayout_eGridPanel"]/div[1]/div/div[4]/div[3]/div/div/div[1]/div[2]/div/span')
      .pause(waitTime)
      .click('//*[@id="borderLayout_eGridPanel"]/div[1]/div/div[4]/div[3]/div/div/div[1]/div[2]/div/div/a[1]')
      .pause(waitTime)
      .click('//*[@id="app"]/div[2]/div/div/div[1]/div/div/div/div/div[2]/img')
      .pause(waitTime)
      .click('//*[@id="undefined"]/div/div/ul/li/div[1]/div[2]/i')
      .pause(waitTime)
      .click('//*[@id="undefined"]/div/div/ul/li/div[1]/div[2]/i')
      .pause(waitTime)
      .click('//*[@id="app"]/div[2]/div/div/div[1]/div/div/div/div/div[2]/div/div/div[2]/div/div/div[1]/div/div/div[3]/a')
      .pause(waitTime)
      .click('//*[@id="app"]/div[2]/div/div/div[1]/div/div/div/div/div[2]/div/div/div[2]/div/div/div[1]/div/div/div[2]/a')
      .pause(waitTime)
      .click('//*[@id="app"]/div[2]/div/div/div[1]/div/div/div/div/div[1]/div/button/div')
      .pause(waitTime)
      .click('//*[@id="app"]/div[2]/div/div/div[2]/div[2]/div/div[1]/div/button/div')
      .pause(waitTime)
      .click('//*[@id="name"]/a')
      .pause(waitTime)
      .click('//*[@id="not-closed"]/a')
      .pause(waitTime)
      .click('//*[@id="recycle-schedule"]/a')
      .pause(waitTime)
      .click('//*[@id="etc-facility"]/a')
      .pause(waitTime)
      .click('//*[@id="app"]/div[2]/div/div/div[2]/div[2]/div/div[2]/div[1]/div/button[2]/div')
      .pause(waitTime)
      .click('//*[@id="app"]/div[2]/div/div/div[2]/div[2]/div/div[2]/div[1]/div/button[1]/div')
      .pause(waitTime)
      .click('//*[@id="app"]/div[2]/div/div/div[2]/div[2]/div/div[1]/div/button/div')
      .pause(waitTime)
      .setValue('//*[@id="app"]/div[2]/div/div/div[2]/div[2]/div/div[1]/div/div/div/div[1]/input', 'ultra')
      .keys('\uE007') // To press Enter key.
      .pause(waitTime)
      .click('//*[@id="app"]/div[1]/div[2]/ul/li[3]/button/div')
      .pause(waitTime)
  },
  after: function (browser) {
    browser
      .end()
  }
}
