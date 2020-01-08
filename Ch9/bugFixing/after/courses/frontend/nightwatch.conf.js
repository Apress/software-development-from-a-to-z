require('babel-register')

// http://nightwatchjs.org/guide#settings-file
module.exports = {
  'src_folders': ['tests/e2e'],
  'output_folder': 'tests/e2e',
  'selenium': {
    'start_process': false,
    'server_path': 'node_modules/selenium-server/lib/runner/selenium-server-standalone-3.13.0.jar',
    'port': 4445,
    'cli_args': {
      'webdriver.chrome.driver': require('chromedriver').path
    }
  },

  'test_settings': {
    'default': {
      'selenium_port': 4445,
      'selenium_host': 'localhost',
      'silent': false,
      'screenshots': {
        'enabled': false,
        'path': ''
      },
      'desiredCapabilities': {
        'browserName': 'firefox',
        'marionette': true
      }
    },

    'chrome': {
      'desiredCapabilities': {
        'browserName': 'chrome',
        'javascriptEnabled': true,
        'acceptSslCerts': true
      }
    },

    'firefox': {
      'desiredCapabilities': {
        'browserName': 'firefox',
        'javascriptEnabled': true,
        'acceptSslCerts': true
      }
    }
  }
}