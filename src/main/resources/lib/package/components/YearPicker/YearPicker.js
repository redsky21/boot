var _interopRequireDefault = require("@babel/runtime/helpers/interopRequireDefault");

var _typeof = require("@babel/runtime/helpers/typeof");

Object.defineProperty(exports, "__esModule", {
  value: true
});
exports["default"] = void 0;

var _extends2 = _interopRequireDefault(require("@babel/runtime/helpers/extends"));

var _defineProperty2 = _interopRequireDefault(require("@babel/runtime/helpers/defineProperty"));

var _objectWithoutProperties2 = _interopRequireDefault(require("@babel/runtime/helpers/objectWithoutProperties"));

var _react = _interopRequireWildcard(require("react"));

var _classnames = _interopRequireDefault(require("classnames"));

var _AdapterDateFns = _interopRequireDefault(require("@mui/lab/AdapterDateFns"));

var _LocalizationProvider = _interopRequireDefault(require("@mui/lab/LocalizationProvider"));

var _DatePicker = _interopRequireDefault(require("@mui/lab/DatePicker"));

var _TextField = _interopRequireDefault(require("../TextField"));

var _styles = require("@mui/material/styles");

var _styles2 = require("@mui/styles");

var _excluded = ["value", "onChange", "label", "labelAlign", "labelWidth", "required", "error", "disableMonthPicker", "WrapperProps"];

function _getRequireWildcardCache(nodeInterop) { if (typeof WeakMap !== "function") return null; var cacheBabelInterop = new WeakMap(); var cacheNodeInterop = new WeakMap(); return (_getRequireWildcardCache = function _getRequireWildcardCache(nodeInterop) { return nodeInterop ? cacheNodeInterop : cacheBabelInterop; })(nodeInterop); }

function _interopRequireWildcard(obj, nodeInterop) { if (!nodeInterop && obj && obj.__esModule) { return obj; } if (obj === null || _typeof(obj) !== "object" && typeof obj !== "function") { return { "default": obj }; } var cache = _getRequireWildcardCache(nodeInterop); if (cache && cache.has(obj)) { return cache.get(obj); } var newObj = {}; var hasPropertyDescriptor = Object.defineProperty && Object.getOwnPropertyDescriptor; for (var key in obj) { if (key !== "default" && Object.prototype.hasOwnProperty.call(obj, key)) { var desc = hasPropertyDescriptor ? Object.getOwnPropertyDescriptor(obj, key) : null; if (desc && (desc.get || desc.set)) { Object.defineProperty(newObj, key, desc); } else { newObj[key] = obj[key]; } } } newObj["default"] = obj; if (cache) { cache.set(obj, newObj); } return newObj; }

function ownKeys(object, enumerableOnly) { var keys = Object.keys(object); if (Object.getOwnPropertySymbols) { var symbols = Object.getOwnPropertySymbols(object); enumerableOnly && (symbols = symbols.filter(function (sym) { return Object.getOwnPropertyDescriptor(object, sym).enumerable; })), keys.push.apply(keys, symbols); } return keys; }

function _objectSpread(target) { for (var i = 1; i < arguments.length; i++) { var source = null != arguments[i] ? arguments[i] : {}; i % 2 ? ownKeys(Object(source), !0).forEach(function (key) { (0, _defineProperty2["default"])(target, key, source[key]); }) : Object.getOwnPropertyDescriptors ? Object.defineProperties(target, Object.getOwnPropertyDescriptors(source)) : ownKeys(Object(source)).forEach(function (key) { Object.defineProperty(target, key, Object.getOwnPropertyDescriptor(source, key)); }); } return target; }

var usePaperStyles = (0, _styles2.makeStyles)({
  root: {
    width: 'auto',
    '& .css-1dozdou': {
      display: 'block'
    },
    '& .MuiMonthPicker-root': {
      width: 'auto',
      '& .Mui-selected': {
        fontSize: '1.6rem',
        fontWeight: '600',
        '&:hover': {
          backgroundColor: '#F24851 !important',
          color: '#fff'
        }
      }
    },
    '& .MuiCalendarPicker-viewTransitionContainer': {
      '& .MuiMonthPicker-root': {
        '& .MuiTypography-root': {
          '&:active': {
            fontSize: '1.6rem'
          },
          '&:hover': {
            backgroundColor: 'rgba(100, 100, 100, 0.1)'
          }
        }
      },
      '& .MuiYearPicker-root': {
        '& .PrivatePickersYear-root': {
          '& .PrivatePickersYear-yearButton': {
            '&.Mui-selected': {
              backgroundColor: '#F24851',
              '&:hover': {
                backgroundColor: '#F24851',
                color: '#fff'
              }
            }
          }
        }
      }
    }
  }
});

function YearPicker(_ref) {
  var value = _ref.value,
      onChange = _ref.onChange,
      label = _ref.label,
      labelAlign = _ref.labelAlign,
      labelWidth = _ref.labelWidth,
      required = _ref.required,
      error = _ref.error,
      _ref$disableMonthPick = _ref.disableMonthPicker,
      disableMonthPicker = _ref$disableMonthPick === void 0 ? true : _ref$disableMonthPick,
      _ref$WrapperProps = _ref.WrapperProps,
      WrapperProps = _ref$WrapperProps === void 0 ? {} : _ref$WrapperProps,
      rest = (0, _objectWithoutProperties2["default"])(_ref, _excluded);
  WrapperProps = _objectSpread(_objectSpread({}, WrapperProps), {}, {
    className: (0, _classnames["default"])('cnsui-yearpicker', WrapperProps['className'])
  });
  var classes = usePaperStyles();
  var views = (0, _react.useMemo)(function () {
    var output = ['year'];

    if (!disableMonthPicker) {
      output.push('month');
    }

    return output;
  }, [disableMonthPicker]);
  return /*#__PURE__*/_react["default"].createElement(_LocalizationProvider["default"], {
    dateAdapter: _AdapterDateFns["default"]
  }, /*#__PURE__*/_react["default"].createElement(_DatePicker["default"], (0, _extends2["default"])({
    views: views,
    value: value,
    onChange: onChange,
    PopperProps: {
      style: popperStyle
    },
    PaperProps: {
      classes: classes
    },
    renderInput: function renderInput(params) {
      return /*#__PURE__*/_react["default"].createElement(StyledTextField, (0, _extends2["default"])({}, params, {
        label: label,
        labelAlign: labelAlign,
        labelWidth: labelWidth,
        required: required,
        error: error,
        WrapperProps: WrapperProps,
        helperText: null
      }));
    }
  }, rest)));
}

var _default = YearPicker;
exports["default"] = _default;
var popperStyle = {};
var StyledTextField = (0, _styles.styled)(_TextField["default"])({
  '.YearMonthPicker-wrap &': {
    '& .MuiInput-root': {
      marginRight: '7.5rem',
      '& .MuiInputBase-input': {
        width: '12rem'
      }
    }
  },
  '&': {
    '& .MuiInput-root': {
      marginRight: '10rem',
      '& .MuiInputBase-input': {
        width: '10rem',
        paddingRight: '0',
        color: 'var(--rs-text-secondary)',
        fontWeight: '400'
      },
      '& button.MuiIconButton-root': {
        width: '3.2rem',
        height: '3.2rem',
        padding: '0',
        minWidth: 'initial',
        margin: '0',
        borderRadius: '3.2rem',
        '& .MuiSvgIcon-root': {
          marginTop: '2px',
          color: 'var(--rs-text-secondary)',
          fontSize: '1.6rem'
        }
      }
    }
  }
});