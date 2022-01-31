var _interopRequireDefault = require("@babel/runtime/helpers/interopRequireDefault");

var _typeof = require("@babel/runtime/helpers/typeof");

Object.defineProperty(exports, "__esModule", {
  value: true
});
exports["default"] = void 0;

var _extends2 = _interopRequireDefault(require("@babel/runtime/helpers/extends"));

var _objectWithoutProperties2 = _interopRequireDefault(require("@babel/runtime/helpers/objectWithoutProperties"));

var _react = _interopRequireWildcard(require("react"));

var _TextField = _interopRequireDefault(require("../TextField"));

var _nanoid = require("nanoid");

var _styles = require("@mui/material/styles");

var _excluded = ["type", "value", "onChange", "WrapperProps", "useResize", "disabled", "width", "minRows", "maxRows"];

function _getRequireWildcardCache(nodeInterop) { if (typeof WeakMap !== "function") return null; var cacheBabelInterop = new WeakMap(); var cacheNodeInterop = new WeakMap(); return (_getRequireWildcardCache = function _getRequireWildcardCache(nodeInterop) { return nodeInterop ? cacheNodeInterop : cacheBabelInterop; })(nodeInterop); }

function _interopRequireWildcard(obj, nodeInterop) { if (!nodeInterop && obj && obj.__esModule) { return obj; } if (obj === null || _typeof(obj) !== "object" && typeof obj !== "function") { return { "default": obj }; } var cache = _getRequireWildcardCache(nodeInterop); if (cache && cache.has(obj)) { return cache.get(obj); } var newObj = {}; var hasPropertyDescriptor = Object.defineProperty && Object.getOwnPropertyDescriptor; for (var key in obj) { if (key !== "default" && Object.prototype.hasOwnProperty.call(obj, key)) { var desc = hasPropertyDescriptor ? Object.getOwnPropertyDescriptor(obj, key) : null; if (desc && (desc.get || desc.set)) { Object.defineProperty(newObj, key, desc); } else { newObj[key] = obj[key]; } } } newObj["default"] = obj; if (cache) { cache.set(obj, newObj); } return newObj; }

function Textarea(_ref) {
  var type = _ref.type,
      value = _ref.value,
      onChange = _ref.onChange,
      _ref$WrapperProps = _ref.WrapperProps,
      WrapperProps = _ref$WrapperProps === void 0 ? {} : _ref$WrapperProps,
      _ref$useResize = _ref.useResize,
      useResize = _ref$useResize === void 0 ? false : _ref$useResize,
      disabled = _ref.disabled,
      _ref$width = _ref.width,
      width = _ref$width === void 0 ? 200 : _ref$width,
      _ref$minRows = _ref.minRows,
      minRows = _ref$minRows === void 0 ? 3 : _ref$minRows,
      _ref$maxRows = _ref.maxRows,
      maxRows = _ref$maxRows === void 0 ? 6 : _ref$maxRows,
      rest = (0, _objectWithoutProperties2["default"])(_ref, _excluded);
  var hash = (0, _react.useMemo)(function () {
    return (0, _nanoid.nanoid)(6);
  }, []);
  return /*#__PURE__*/_react["default"].createElement(StyledTextField, (0, _extends2["default"])({
    sx: {
      width: width || 'auto',
      '& .MuiInputBase-inputMultiline': {
        resize: useResize ? 'both' : 'none'
      }
    },
    multiline: true,
    variant: "outlined",
    minRows: minRows,
    maxRows: maxRows,
    value: value,
    disabled: disabled,
    onChange: onChange,
    inputProps: {
      id: hash
    }
  }, rest));
}

var _default = Textarea;
exports["default"] = _default;
var StyledTextField = (0, _styles.styled)(_TextField["default"])({
  position: 'relative',
  paddingTop: 0,
  '& .MuiInputBase-multiline': {
    padding: 0,
    borderBottom: 0,
    backgroundColor: '#fff !important'
  },
  '& .MuiInputBase-inputMultiline': {
    paddingTop: '1rem !important',
    paddingBottom: '1rem !important',
    lineHeight: 'normal !important'
  }
});