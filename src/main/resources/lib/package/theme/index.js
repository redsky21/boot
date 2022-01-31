var _interopRequireDefault = require("@babel/runtime/helpers/interopRequireDefault");

Object.defineProperty(exports, "__esModule", {
  value: true
});
exports.CNSThemeProvider = void 0;
Object.defineProperty(exports, "globalStyles", {
  enumerable: true,
  get: function get() {
    return _globalStyles["default"];
  }
});

var _react = _interopRequireDefault(require("react"));

var _styles = require("@mui/material/styles");

var _globalStyles = _interopRequireDefault(require("./globalStyles"));

var _react2 = require("@emotion/react");

var _defs = _interopRequireDefault(require("./defs"));

var _reactToastNotifications = require("react-toast-notifications");

var _Toast = _interopRequireDefault(require("../components/Toast"));

var _customStyles = _interopRequireDefault(require("./customStyles"));

var _useMediaQuery = _interopRequireDefault(require("@mui/material/useMediaQuery"));

var CNSThemeProvider = function CNSThemeProvider(_ref) {
  var children = _ref.children;
  var isDesktop = (0, _useMediaQuery["default"])('(min-width: 1024px)');
  return /*#__PURE__*/_react["default"].createElement(_react["default"].Fragment, null, /*#__PURE__*/_react["default"].createElement(_react2.Global, {
    styles: _globalStyles["default"]
  }), /*#__PURE__*/_react["default"].createElement(_react2.Global, {
    styles: _customStyles["default"]
  }), /*#__PURE__*/_react["default"].createElement(_styles.ThemeProvider, {
    theme: _defs["default"]
  }, /*#__PURE__*/_react["default"].createElement(_reactToastNotifications.ToastProvider, {
    autoDismiss: true,
    components: {
      Toast: _Toast["default"]
    },
    placement: isDesktop ? 'bottom-right' : 'top-center'
  }, children)));
};

exports.CNSThemeProvider = CNSThemeProvider;