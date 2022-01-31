var _interopRequireDefault = require("@babel/runtime/helpers/interopRequireDefault");

Object.defineProperty(exports, "__esModule", {
  value: true
});
exports["default"] = PendingFallback;

var _extends2 = _interopRequireDefault(require("@babel/runtime/helpers/extends"));

var _objectWithoutProperties2 = _interopRequireDefault(require("@babel/runtime/helpers/objectWithoutProperties"));

var _react = _interopRequireDefault(require("react"));

var _Fallback = _interopRequireDefault(require("../Fallback"));

var _OverlaySpinner = _interopRequireDefault(require("../OverlaySpinner"));

var _excluded = ["size"];

function PendingFallback(_ref) {
  var size = _ref.size,
      rest = (0, _objectWithoutProperties2["default"])(_ref, _excluded);
  return /*#__PURE__*/_react["default"].createElement(_Fallback["default"], (0, _extends2["default"])({
    style: {
      backgroundColor: 'transparent'
    },
    size: size
  }, rest), /*#__PURE__*/_react["default"].createElement(_OverlaySpinner["default"], null));
}