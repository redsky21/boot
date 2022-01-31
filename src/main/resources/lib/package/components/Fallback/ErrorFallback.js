var _interopRequireDefault = require("@babel/runtime/helpers/interopRequireDefault");

Object.defineProperty(exports, "__esModule", {
  value: true
});
exports["default"] = ErrorFallback;

var _extends2 = _interopRequireDefault(require("@babel/runtime/helpers/extends"));

var _objectWithoutProperties2 = _interopRequireDefault(require("@babel/runtime/helpers/objectWithoutProperties"));

var _react = _interopRequireDefault(require("react"));

var _Fallback = _interopRequireDefault(require("../Fallback"));

var _NoResultFallback = require("./NoResultFallback");

var _excluded = ["children", "size"];

function ErrorFallback(_ref) {
  var children = _ref.children,
      size = _ref.size,
      rest = (0, _objectWithoutProperties2["default"])(_ref, _excluded);
  return /*#__PURE__*/_react["default"].createElement(_Fallback["default"], (0, _extends2["default"])({
    size: size
  }, rest), /*#__PURE__*/_react["default"].createElement(_NoResultFallback.Information, null, "!"), children ? children : /*#__PURE__*/_react["default"].createElement("p", null, "An unexpected error has occured."));
}