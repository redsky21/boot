var _interopRequireDefault = require("@babel/runtime/helpers/interopRequireDefault");

Object.defineProperty(exports, "__esModule", {
  value: true
});
exports["default"] = void 0;

var _extends2 = _interopRequireDefault(require("@babel/runtime/helpers/extends"));

var _objectWithoutProperties2 = _interopRequireDefault(require("@babel/runtime/helpers/objectWithoutProperties"));

var _react = _interopRequireDefault(require("react"));

var _classnames = _interopRequireDefault(require("classnames"));

var _Checkbox = _interopRequireDefault(require("@mui/material/Checkbox"));

var _excluded = ["color", "className"];

var Checkbox = /*#__PURE__*/_react["default"].forwardRef(function (_ref, ref) {
  var _ref$color = _ref.color,
      color = _ref$color === void 0 ? 'primary' : _ref$color,
      className = _ref.className,
      rest = (0, _objectWithoutProperties2["default"])(_ref, _excluded);
  return /*#__PURE__*/_react["default"].createElement(_Checkbox["default"], (0, _extends2["default"])({
    sx: {
      root: {
        padding: '0 !important',
        '&:hover': {
          backgroundColor: 'transparent !important'
        }
      }
    },
    ref: ref,
    color: color,
    disableRipple: true,
    className: (0, _classnames["default"])('cnsui-checkbox', className)
  }, rest));
});

var _default = Checkbox;
exports["default"] = _default;