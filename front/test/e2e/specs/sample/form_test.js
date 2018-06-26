// For authoring Nightwatch tests, see
// http://nightwatchjs.org/guide#usage

module.exports = {
  'form tests': function (browser) {
    // automatically uses dev Server port from /config.index.js
    // default: http://localhost:8080
    // see nightwatch.conf.js
    const devServer = browser.globals.devServerURL;
    const waitTime = browser.globals.waitTime;

    browser
      .url("http://localhost:6006/?selectedKind=Form&selectedStory=FormTest&full=0&addons=1&stories=1&panelRight=0&addonPanel=storybook%2Factions%2Factions-panel")
      .pause(waitTime)
      .frame('storybook-preview-iframe')
      .waitForElementVisible('#name', waitTime)
      .setValue('#name', 'rikko')
      .setValue('#birth', '1987-03-14')
      .setValue('#age', '53')
      .setValue('#email', 'dd')
      .setValue('#url', 'http://naver.com')
      .setValue('#id', 'riko4342')
      .click("#jobA")
      .click("#jobC")
      .click("#Female")
      .click('#location option[value=B]')
      .click(".btn-primary")
      .pause(waitTime)
      .end()
  }
};
