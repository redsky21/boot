var _interopRequireDefault = require("@babel/runtime/helpers/interopRequireDefault");

Object.defineProperty(exports, "__esModule", {
  value: true
});
exports["default"] = Select;

var _extends2 = _interopRequireDefault(require("@babel/runtime/helpers/extends"));

var _defineProperty2 = _interopRequireDefault(require("@babel/runtime/helpers/defineProperty"));

var _objectWithoutProperties2 = _interopRequireDefault(require("@babel/runtime/helpers/objectWithoutProperties"));

var _react = _interopRequireDefault(require("react"));

var _classnames = _interopRequireDefault(require("classnames"));

var _TextField = _interopRequireDefault(require("../TextField"));

var _styles = require("@mui/material/styles");

var _SelectItem = _interopRequireDefault(require("./SelectItem"));

var _excluded = ["children", "placeholder", "value", "width", "useNoSelect", "WrapperProps", "onChange", "className"];

function ownKeys(object, enumerableOnly) { var keys = Object.keys(object); if (Object.getOwnPropertySymbols) { var symbols = Object.getOwnPropertySymbols(object); enumerableOnly && (symbols = symbols.filter(function (sym) { return Object.getOwnPropertyDescriptor(object, sym).enumerable; })), keys.push.apply(keys, symbols); } return keys; }

function _objectSpread(target) { for (var i = 1; i < arguments.length; i++) { var source = null != arguments[i] ? arguments[i] : {}; i % 2 ? ownKeys(Object(source), !0).forEach(function (key) { (0, _defineProperty2["default"])(target, key, source[key]); }) : Object.getOwnPropertyDescriptors ? Object.defineProperties(target, Object.getOwnPropertyDescriptors(source)) : ownKeys(Object(source)).forEach(function (key) { Object.defineProperty(target, key, Object.getOwnPropertyDescriptor(source, key)); }); } return target; }

function Select(_ref) {
  var children = _ref.children,
      _ref$placeholder = _ref.placeholder,
      placeholder = _ref$placeholder === void 0 ? 'Select' : _ref$placeholder,
      value = _ref.value,
      width = _ref.width,
      _ref$useNoSelect = _ref.useNoSelect,
      useNoSelect = _ref$useNoSelect === void 0 ? true : _ref$useNoSelect,
      _ref$WrapperProps = _ref.WrapperProps,
      WrapperProps = _ref$WrapperProps === void 0 ? {} : _ref$WrapperProps,
      onChange = _ref.onChange,
      className = _ref.className,
      rest = (0, _objectWithoutProperties2["default"])(_ref, _excluded);
  WrapperProps = _objectSpread(_objectSpread({}, WrapperProps), {}, {
    className: (0, _classnames["default"])('cnsui-select', className)
  });
  return /*#__PURE__*/_react["default"].createElement(StyledTextField, (0, _extends2["default"])({
    select: true,
    value: value,
    onChange: onChange,
    WrapperProps: WrapperProps,
    sx: {
      width: width || '24rem'
    },
    SelectProps: {
      defaultValue: '',
      displayEmpty: true,
      MenuProps: {
        sx: {
          '& .MuiMenu-paper': {
            padding: '0 !important',
            border: '0 !important',
            borderRadius: '0.4rem !important',
            boxShadow: '0px 5px 5px -3px rgb(0 0 0 / 20%), 0px 8px 10px 1px rgb(0 0 0 / 14%), 0px 3px 14px 2px rgb(0 0 0 / 12%) !important'
          },
          '& .MuiMenuItem-root': {
            fontSize: '1.4rem',
            fontWeight: 'normal',
            color: '#000'
          }
        }
      }
    }
  }, rest), /*#__PURE__*/_react["default"].createElement(_SelectItem["default"], {
    disabled: !useNoSelect,
    value: '',
    style: _objectSpread({
      display: 'none'
    }, useNoSelect && {
      display: 'block',
      opacity: 0.5
    })
  }, placeholder), children);
}

var StyledTextField = (0, _styles.styled)(_TextField["default"])({
  '& .MuiSelect-root': {
    padding: 0
  },
  '& .MuiSelect-select': {
    width: '100%',
    backgroundColor: 'inherit !important',
    fontSize: '1.4rem',
    padding: '.85rem 1.8rem .85rem 1.3rem !important',
    lineHeight: 1
  },
  '& .MuiSelect-iconStandard': {
    fontSize: '2rem',
    right: 0,
    color: '#000'
  }
});