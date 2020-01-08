module.exports = {
  'Test Registration page': function (browser) {
    browser
      .url('http://localhost:3000/register')
      .waitForElementVisible('#app', 5000)
      .assert.containsText('.input-group__counter', '0 / 32')
      .setValue('input[autocomplete="name"]', '1234567890123')
      .assert.containsText('.input-group__counter', '13 / 32')
      .end()
  }
}
