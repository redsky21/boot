Object.defineProperty(exports, "__esModule", {
  value: true
});
exports.useToast = useToast;

var _react = require("react");

var _reactToastNotifications = require("react-toast-notifications");

function useToast() {
  var _useToasts = (0, _reactToastNotifications.useToasts)(),
      addToast = _useToasts.addToast;

  var handleToastAdd = (0, _react.useCallback)(function () {
    var type = arguments.length > 0 && arguments[0] !== undefined ? arguments[0] : 'info';
    var message = arguments.length > 1 && arguments[1] !== undefined ? arguments[1] : '';
    addToast(message, {
      appearance: type
    });
  }, [addToast]);
  return handleToastAdd;
}