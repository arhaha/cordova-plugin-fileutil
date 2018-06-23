var exec = require('cordova/exec');

exports.readFileAsBytes = function (arg0, success, error) {
    exec(success, error, 'FileUtil', 'readFileAsBytes', [arg0]);
};
