var _interopRequireDefault = require("@babel/runtime/helpers/interopRequireDefault");

Object.defineProperty(exports, "__esModule", {
  value: true
});
exports["default"] = SelectItem;

var _extends2 = _interopRequireDefault(require("@babel/runtime/helpers/extends"));

var _objectWithoutProperties2 = _interopRequireDefault(require("@babel/runtime/helpers/objectWithoutProperties"));

var _react = _interopRequireDefault(require("react"));

var _classnames = _interopRequireDefault(require("classnames"));

var _MenuItem = _interopRequireDefault(require("@mui/material/MenuItem"));

var _excluded = ["children", "className"];

function SelectItem(_ref) {
  var children = _ref.children,
      className = _ref.className,
      rest = (0, _objectWithoutProperties2["default"])(_ref, _excluded);
  return /*#__PURE__*/_react["default"].createElement(_MenuItem["default"], (0, _extends2["default"])({
    className: (0, _classnames["default"])('cnsui-menuitem', className)
  }, rest), children);
}