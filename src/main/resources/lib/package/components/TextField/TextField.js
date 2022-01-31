var _interopRequireDefault = require("@babel/runtime/helpers/interopRequireDefault");

var _typeof = require("@babel/runtime/helpers/typeof");

Object.defineProperty(exports, "__esModule", {
  value: true
});
exports["default"] = void 0;

var _extends2 = _interopRequireDefault(require("@babel/runtime/helpers/extends"));

var _defineProperty2 = _interopRequireDefault(require("@babel/runtime/helpers/defineProperty"));

var _objectWithoutProperties2 = _interopRequireDefault(require("@babel/runtime/helpers/objectWithoutProperties"));

var _base = _interopRequireDefault(require("@emotion/styled/base"));

var _react = _interopRequireWildcard(require("react"));

var _classnames = _interopRequireDefault(require("classnames"));

var _TextField = _interopRequireDefault(require("@mui/material/TextField"));

var _nanoid = require("nanoid");

var _InputAdornment = _interopRequireDefault(require("@mui/material/InputAdornment"));

var _SearchIcon = _interopRequireDefault(require("./SearchIcon"));

var _Clear = _interopRequireDefault(require("@mui/icons-material/Clear"));

var _colors = _interopRequireDefault(require("../../theme/colors"));

var _deepmerge = _interopRequireDefault(require("deepmerge"));

var _styles = require("@mui/material/styles");

var _OverlaySpinner = _interopRequireDefault(require("../OverlaySpinner"));

var _InputWrapper = _interopRequireDefault(require("../InputWrapper"));

var _excluded = ["type", "label", "value", "onChange", "labelAlign", "labelWidth", "WrapperProps", "required", "disabled", "readOnly", "error", "fullWidth", "width", "SearchProps", "sx"];

function _getRequireWildcardCache(nodeInterop) { if (typeof WeakMap !== "function") return null; var cacheBabelInterop = new WeakMap(); var cacheNodeInterop = new WeakMap(); return (_getRequireWildcardCache = function _getRequireWildcardCache(nodeInterop) { return nodeInterop ? cacheNodeInterop : cacheBabelInterop; })(nodeInterop); }

function _interopRequireWildcard(obj, nodeInterop) { if (!nodeInterop && obj && obj.__esModule) { return obj; } if (obj === null || _typeof(obj) !== "object" && typeof obj !== "function") { return { "default": obj }; } var cache = _getRequireWildcardCache(nodeInterop); if (cache && cache.has(obj)) { return cache.get(obj); } var newObj = {}; var hasPropertyDescriptor = Object.defineProperty && Object.getOwnPropertyDescriptor; for (var key in obj) { if (key !== "default" && Object.prototype.hasOwnProperty.call(obj, key)) { var desc = hasPropertyDescriptor ? Object.getOwnPropertyDescriptor(obj, key) : null; if (desc && (desc.get || desc.set)) { Object.defineProperty(newObj, key, desc); } else { newObj[key] = obj[key]; } } } newObj["default"] = obj; if (cache) { cache.set(obj, newObj); } return newObj; }

function ownKeys(object, enumerableOnly) { var keys = Object.keys(object); if (Object.getOwnPropertySymbols) { var symbols = Object.getOwnPropertySymbols(object); enumerableOnly && (symbols = symbols.filter(function (sym) { return Object.getOwnPropertyDescriptor(object, sym).enumerable; })), keys.push.apply(keys, symbols); } return keys; }

function _objectSpread(target) { for (var i = 1; i < arguments.length; i++) { var source = null != arguments[i] ? arguments[i] : {}; i % 2 ? ownKeys(Object(source), !0).forEach(function (key) { (0, _defineProperty2["default"])(target, key, source[key]); }) : Object.getOwnPropertyDescriptors ? Object.defineProperties(target, Object.getOwnPropertyDescriptors(source)) : ownKeys(Object(source)).forEach(function (key) { Object.defineProperty(target, key, Object.getOwnPropertyDescriptor(source, key)); }); } return target; }

function _EMOTION_STRINGIFIED_CSS_ERROR__() { return "You have tried to stringify object returned from `css` function. It isn't supposed to be used directly (e.g. as value of the `className` prop), but rather handed to emotion so it can handle it (e.g. as value of `css` prop)."; }

function TextField(_ref) {
  var _SearchProps$isLoadin;

  var type = _ref.type,
      label = _ref.label,
      value = _ref.value,
      onChange = _ref.onChange,
      labelAlign = _ref.labelAlign,
      labelWidth = _ref.labelWidth,
      _ref$WrapperProps = _ref.WrapperProps,
      WrapperProps = _ref$WrapperProps === void 0 ? {} : _ref$WrapperProps,
      required = _ref.required,
      disabled = _ref.disabled,
      _ref$readOnly = _ref.readOnly,
      readOnly = _ref$readOnly === void 0 ? false : _ref$readOnly,
      error = _ref.error,
      fullWidth = _ref.fullWidth,
      width = _ref.width,
      SearchProps = _ref.SearchProps,
      _ref$sx = _ref.sx,
      sx = _ref$sx === void 0 ? {} : _ref$sx,
      rest = (0, _objectWithoutProperties2["default"])(_ref, _excluded);
  var hash = (0, _react.useMemo)(function () {
    return (0, _nanoid.nanoid)(6);
  }, []);
  var customStyleDef = (0, _react.useMemo)(function () {
    return (0, _deepmerge["default"])({
      width: width || '24rem'
    }, sx);
  }, [width, sx]);
  var wrapperClassName = (0, _classnames["default"])('cnsui-textfield', WrapperProps['className']);
  return /*#__PURE__*/_react["default"].createElement(_InputWrapper["default"], (0, _extends2["default"])({
    htmlFor: hash,
    label: label,
    labelAlign: labelAlign,
    labelWidth: labelWidth,
    required: required,
    error: error,
    className: wrapperClassName,
    fullWidth: fullWidth
  }, WrapperProps), /*#__PURE__*/_react["default"].createElement(StyledTextField, (0, _extends2["default"])({
    sx: customStyleDef,
    variant: "standard",
    type: type,
    error: error,
    multiline: false,
    select: false,
    required: required,
    value: value,
    disabled: disabled || Boolean(SearchProps === null || SearchProps === void 0 ? void 0 : SearchProps.isLoading),
    onChange: onChange,
    inputProps: {
      id: hash
    },
    InputProps: _objectSpread({
      readOnly: readOnly
    }, type === 'search' && {
      endAdornment: /*#__PURE__*/_react["default"].createElement(_InputAdornment["default"], {
        position: "end"
      }, value && !disabled && /*#__PURE__*/_react["default"].createElement(ClearButton, {
        type: "button",
        tabIndex: -1,
        onClick: SearchProps === null || SearchProps === void 0 ? void 0 : SearchProps.onClear
      }, /*#__PURE__*/_react["default"].createElement(_Clear["default"], {
        sx: {
          fontSize: '1.5rem'
        }
      })), /*#__PURE__*/_react["default"].createElement(SearchButton, {
        type: "button",
        tabIndex: -1,
        isLoading: (_SearchProps$isLoadin = SearchProps === null || SearchProps === void 0 ? void 0 : SearchProps.isLoading) !== null && _SearchProps$isLoadin !== void 0 ? _SearchProps$isLoadin : false,
        onClick: !disabled ? SearchProps === null || SearchProps === void 0 ? void 0 : SearchProps.onSearchClick : undefined
      }, (SearchProps === null || SearchProps === void 0 ? void 0 : SearchProps.isLoading) && /*#__PURE__*/_react["default"].createElement(_OverlaySpinner["default"], {
        color: "primary",
        size: "1.5rem"
      }), /*#__PURE__*/_react["default"].createElement(_SearchIcon["default"], {
        width: "1.5rem",
        height: "1.5rem"
      })))
    })
  }, rest)));
}

var _default = TextField;
exports["default"] = _default;
var StyledTextField = (0, _styles.styled)(_TextField["default"])({
  backgroundColor: 'inherit',
  '& .MuiInput-root': {
    fontSize: '1.4rem',
    '&:before, &:after': {
      borderBottom: '0 !important'
    },
    '&.Mui-disabled': {
      opacity: 0.75,
      cursor: 'not-allowed',
      '& [type="button"]': {
        cursor: 'not-allowed',
        userSelect: 'none',
        zIndex: '-1'
      }
    }
  },
  '& .MuiFormHelperText-root': {
    fontSize: '1.2rem',
    margin: '0.5rem 0.25rem 0',
    display: 'block',
    overflow: 'hidden',
    textOverflow: 'ellipsis',
    '&.Mui-error': {
      color: _colors["default"]['danger-600']
    }
  },
  '& .MuiInput-underline': {
    borderBottom: '1px solid rgba(0,0,0,0.2)'
  },
  '& .MuiInputBase-input': {
    height: '3.4rem',
    lineHeight: '3.4rem',
    backgroundColor: 'inherit',
    paddingTop: 0,
    paddingBottom: 0,
    '&[readonly]': {
      cursor: 'default'
    }
  },
  '& .MuiInputBase-inputAdornedStart': {
    paddingLeft: 0
  }
});
var ClearButton = (0, _base["default"])("button", process.env.NODE_ENV === "production" ? {
  target: "e1s91y4f1"
} : {
  target: "e1s91y4f1",
  label: "ClearButton"
})(process.env.NODE_ENV === "production" ? {
  name: "uu59lo",
  styles: "display:inline-flex;svg{color:#bbb;}"
} : {
  name: "uu59lo",
  styles: "display:inline-flex;svg{color:#bbb;}",
  map: "/*# sourceMappingURL=data:application/json;charset=utf-8;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbIi4uLy4uLy4uL3NyYy9jb21wb25lbnRzL1RleHRGaWVsZC9UZXh0RmllbGQudHN4Il0sIm5hbWVzIjpbXSwibWFwcGluZ3MiOiJBQWdLaUMiLCJmaWxlIjoiLi4vLi4vLi4vc3JjL2NvbXBvbmVudHMvVGV4dEZpZWxkL1RleHRGaWVsZC50c3giLCJzb3VyY2VzQ29udGVudCI6WyJpbXBvcnQgUmVhY3QsIHsgdXNlTWVtbyB9IGZyb20gJ3JlYWN0JztcclxuaW1wb3J0IGNuIGZyb20gJ2NsYXNzbmFtZXMnO1xyXG5pbXBvcnQgTXVpVGV4dEZpZWxkLCB7IFRleHRGaWVsZFByb3BzIH0gZnJvbSAnQG11aS9tYXRlcmlhbC9UZXh0RmllbGQnO1xyXG5pbXBvcnQgc3R5bGVkIGZyb20gJ0BlbW90aW9uL3N0eWxlZCc7XHJcbmltcG9ydCB7IG5hbm9pZCB9IGZyb20gJ25hbm9pZCc7XHJcbmltcG9ydCBJbnB1dEFkb3JubWVudCBmcm9tICdAbXVpL21hdGVyaWFsL0lucHV0QWRvcm5tZW50JztcclxuaW1wb3J0IFNlYXJjaEljb24gZnJvbSAnLi9TZWFyY2hJY29uJztcclxuaW1wb3J0IENsZWFySWNvbiBmcm9tICdAbXVpL2ljb25zLW1hdGVyaWFsL0NsZWFyJztcclxuaW1wb3J0IGNvbG9ycyBmcm9tICcuLi8uLi90aGVtZS9jb2xvcnMnO1xyXG5pbXBvcnQgZGVlcG1lcmdlIGZyb20gJ2RlZXBtZXJnZSc7XHJcbmltcG9ydCB7IHN0eWxlZCBhcyBtdWlTdHlsZWQgfSBmcm9tICdAbXVpL21hdGVyaWFsL3N0eWxlcyc7XHJcbmltcG9ydCBPdmVybGF5U3Bpbm5lciBmcm9tICcuLi9PdmVybGF5U3Bpbm5lcic7XHJcbmltcG9ydCBJbnB1dFdyYXBwZXIgZnJvbSAnLi4vSW5wdXRXcmFwcGVyJztcclxuXHJcbnR5cGUgU2VhcmNoUHJvcHMgPSB7XHJcbiAgaXNMb2FkaW5nPzogYm9vbGVhbjtcclxuICBvbkNsZWFyPzogKCkgPT4gdm9pZDtcclxuICBvblNlYXJjaENsaWNrPzogKCkgPT4gdm9pZDtcclxufTtcclxuXHJcbnR5cGUgUHJvcHMgPSB7XHJcbiAgd2lkdGg/OiBzdHJpbmcgfCBudW1iZXI7XHJcbiAgZnVsbFdpZHRoPzogYm9vbGVhbjtcclxuICBsYWJlbEFsaWduPzogJ2xlZnQnIHwgJ3JpZ2h0JztcclxuICBsYWJlbFdpZHRoPzogbnVtYmVyIHwgc3RyaW5nO1xyXG4gIHJlYWRPbmx5PzogYm9vbGVhbjtcclxuICBXcmFwcGVyUHJvcHM/OiBSZWNvcmQ8c3RyaW5nLCBhbnk+O1xyXG4gIFNlYXJjaFByb3BzPzogU2VhcmNoUHJvcHM7XHJcbn07XHJcbmV4cG9ydCB0eXBlIFRUZXh0RmllbGRQcm9wcyA9IFByb3BzICYgT21pdDxUZXh0RmllbGRQcm9wcywga2V5b2YgUHJvcHM+O1xyXG5cclxuZnVuY3Rpb24gVGV4dEZpZWxkKHtcclxuICB0eXBlLFxyXG4gIGxhYmVsLFxyXG4gIHZhbHVlLFxyXG4gIG9uQ2hhbmdlLFxyXG4gIGxhYmVsQWxpZ24sXHJcbiAgbGFiZWxXaWR0aCxcclxuICBXcmFwcGVyUHJvcHMgPSB7fSxcclxuXHJcbiAgcmVxdWlyZWQsXHJcbiAgZGlzYWJsZWQsXHJcbiAgcmVhZE9ubHkgPSBmYWxzZSxcclxuICBlcnJvcixcclxuXHJcbiAgZnVsbFdpZHRoLFxyXG4gIHdpZHRoLFxyXG4gIFNlYXJjaFByb3BzLFxyXG4gIHN4ID0ge30sXHJcbiAgLi4ucmVzdFxyXG59OiBUVGV4dEZpZWxkUHJvcHMpIHtcclxuICBjb25zdCBoYXNoID0gdXNlTWVtbygoKSA9PiBuYW5vaWQoNiksIFtdKTtcclxuXHJcbiAgY29uc3QgY3VzdG9tU3R5bGVEZWYgPSB1c2VNZW1vKFxyXG4gICAgKCkgPT4gZGVlcG1lcmdlPFRUZXh0RmllbGRQcm9wc1snc3gnXT4oeyB3aWR0aDogd2lkdGggfHwgJzI0cmVtJyB9LCBzeCksXHJcbiAgICBbd2lkdGgsIHN4XSxcclxuICApO1xyXG5cclxuICBjb25zdCB3cmFwcGVyQ2xhc3NOYW1lID0gY24oJ2Nuc3VpLXRleHRmaWVsZCcsIFdyYXBwZXJQcm9wc1snY2xhc3NOYW1lJ10pO1xyXG5cclxuICByZXR1cm4gKFxyXG4gICAgPElucHV0V3JhcHBlclxyXG4gICAgICBodG1sRm9yPXtoYXNofVxyXG4gICAgICBsYWJlbD17bGFiZWx9XHJcbiAgICAgIGxhYmVsQWxpZ249e2xhYmVsQWxpZ259XHJcbiAgICAgIGxhYmVsV2lkdGg9e2xhYmVsV2lkdGh9XHJcbiAgICAgIHJlcXVpcmVkPXtyZXF1aXJlZH1cclxuICAgICAgZXJyb3I9e2Vycm9yfVxyXG4gICAgICBjbGFzc05hbWU9e3dyYXBwZXJDbGFzc05hbWV9XHJcbiAgICAgIGZ1bGxXaWR0aD17ZnVsbFdpZHRofVxyXG4gICAgICB7Li4uV3JhcHBlclByb3BzfVxyXG4gICAgPlxyXG4gICAgICA8U3R5bGVkVGV4dEZpZWxkXHJcbiAgICAgICAgc3g9e2N1c3RvbVN0eWxlRGVmfVxyXG4gICAgICAgIHZhcmlhbnQ9XCJzdGFuZGFyZFwiXHJcbiAgICAgICAgdHlwZT17dHlwZX1cclxuICAgICAgICBlcnJvcj17ZXJyb3J9XHJcbiAgICAgICAgbXVsdGlsaW5lPXtmYWxzZX1cclxuICAgICAgICBzZWxlY3Q9e2ZhbHNlfVxyXG4gICAgICAgIHJlcXVpcmVkPXtyZXF1aXJlZH1cclxuICAgICAgICB2YWx1ZT17dmFsdWV9XHJcbiAgICAgICAgZGlzYWJsZWQ9e2Rpc2FibGVkIHx8IEJvb2xlYW4oU2VhcmNoUHJvcHM/LmlzTG9hZGluZyl9XHJcbiAgICAgICAgb25DaGFuZ2U9e29uQ2hhbmdlfVxyXG4gICAgICAgIGlucHV0UHJvcHM9e3sgaWQ6IGhhc2ggfX1cclxuICAgICAgICBJbnB1dFByb3BzPXt7XHJcbiAgICAgICAgICByZWFkT25seSxcclxuICAgICAgICAgIC4uLih0eXBlID09PSAnc2VhcmNoJyAmJiB7XHJcbiAgICAgICAgICAgIGVuZEFkb3JubWVudDogKFxyXG4gICAgICAgICAgICAgIDxJbnB1dEFkb3JubWVudCBwb3NpdGlvbj1cImVuZFwiPlxyXG4gICAgICAgICAgICAgICAge3ZhbHVlICYmICFkaXNhYmxlZCAmJiAoXHJcbiAgICAgICAgICAgICAgICAgIDxDbGVhckJ1dHRvbiB0eXBlPVwiYnV0dG9uXCIgdGFiSW5kZXg9ey0xfSBvbkNsaWNrPXtTZWFyY2hQcm9wcz8ub25DbGVhcn0+XHJcbiAgICAgICAgICAgICAgICAgICAgPENsZWFySWNvbiBzeD17eyBmb250U2l6ZTogJzEuNXJlbScgfX0gLz5cclxuICAgICAgICAgICAgICAgICAgPC9DbGVhckJ1dHRvbj5cclxuICAgICAgICAgICAgICAgICl9XHJcbiAgICAgICAgICAgICAgICA8U2VhcmNoQnV0dG9uXHJcbiAgICAgICAgICAgICAgICAgIHR5cGU9XCJidXR0b25cIlxyXG4gICAgICAgICAgICAgICAgICB0YWJJbmRleD17LTF9XHJcbiAgICAgICAgICAgICAgICAgIGlzTG9hZGluZz17U2VhcmNoUHJvcHM/LmlzTG9hZGluZyA/PyBmYWxzZX1cclxuICAgICAgICAgICAgICAgICAgb25DbGljaz17IWRpc2FibGVkID8gU2VhcmNoUHJvcHM/Lm9uU2VhcmNoQ2xpY2sgOiB1bmRlZmluZWR9XHJcbiAgICAgICAgICAgICAgICA+XHJcbiAgICAgICAgICAgICAgICAgIHtTZWFyY2hQcm9wcz8uaXNMb2FkaW5nICYmIDxPdmVybGF5U3Bpbm5lciBjb2xvcj1cInByaW1hcnlcIiBzaXplPVwiMS41cmVtXCIgLz59XHJcbiAgICAgICAgICAgICAgICAgIDxTZWFyY2hJY29uIHdpZHRoPVwiMS41cmVtXCIgaGVpZ2h0PVwiMS41cmVtXCIgLz5cclxuICAgICAgICAgICAgICAgIDwvU2VhcmNoQnV0dG9uPlxyXG4gICAgICAgICAgICAgIDwvSW5wdXRBZG9ybm1lbnQ+XHJcbiAgICAgICAgICAgICksXHJcbiAgICAgICAgICB9KSxcclxuICAgICAgICB9fVxyXG4gICAgICAgIHsuLi5yZXN0fVxyXG4gICAgICAvPlxyXG4gICAgPC9JbnB1dFdyYXBwZXI+XHJcbiAgKTtcclxufVxyXG5cclxuZXhwb3J0IGRlZmF1bHQgVGV4dEZpZWxkO1xyXG5cclxuY29uc3QgU3R5bGVkVGV4dEZpZWxkID0gbXVpU3R5bGVkKE11aVRleHRGaWVsZCkoe1xyXG4gIGJhY2tncm91bmRDb2xvcjogJ2luaGVyaXQnLFxyXG4gICcmIC5NdWlJbnB1dC1yb290Jzoge1xyXG4gICAgZm9udFNpemU6ICcxLjRyZW0nLFxyXG4gICAgJyY6YmVmb3JlLCAmOmFmdGVyJzoge1xyXG4gICAgICBib3JkZXJCb3R0b206ICcwICFpbXBvcnRhbnQnLFxyXG4gICAgfSxcclxuICAgICcmLk11aS1kaXNhYmxlZCc6IHtcclxuICAgICAgb3BhY2l0eTogMC43NSxcclxuICAgICAgY3Vyc29yOiAnbm90LWFsbG93ZWQnLFxyXG4gICAgICAnJiBbdHlwZT1cImJ1dHRvblwiXSc6IHtcclxuICAgICAgICBjdXJzb3I6ICdub3QtYWxsb3dlZCcsXHJcbiAgICAgICAgdXNlclNlbGVjdDogJ25vbmUnLFxyXG4gICAgICAgIHpJbmRleDogJy0xJyxcclxuICAgICAgfSxcclxuICAgIH0sXHJcbiAgfSxcclxuICAnJiAuTXVpRm9ybUhlbHBlclRleHQtcm9vdCc6IHtcclxuICAgIGZvbnRTaXplOiAnMS4ycmVtJyxcclxuICAgIG1hcmdpbjogJzAuNXJlbSAwLjI1cmVtIDAnLFxyXG4gICAgZGlzcGxheTogJ2Jsb2NrJyxcclxuICAgIG92ZXJmbG93OiAnaGlkZGVuJyxcclxuICAgIHRleHRPdmVyZmxvdzogJ2VsbGlwc2lzJyxcclxuICAgICcmLk11aS1lcnJvcic6IHtcclxuICAgICAgY29sb3I6IGNvbG9yc1snZGFuZ2VyLTYwMCddLFxyXG4gICAgfSxcclxuICB9LFxyXG4gICcmIC5NdWlJbnB1dC11bmRlcmxpbmUnOiB7XHJcbiAgICBib3JkZXJCb3R0b206ICcxcHggc29saWQgcmdiYSgwLDAsMCwwLjIpJyxcclxuICB9LFxyXG4gICcmIC5NdWlJbnB1dEJhc2UtaW5wdXQnOiB7XHJcbiAgICBoZWlnaHQ6ICczLjRyZW0nLFxyXG4gICAgbGluZUhlaWdodDogJzMuNHJlbScsXHJcbiAgICBiYWNrZ3JvdW5kQ29sb3I6ICdpbmhlcml0JyxcclxuICAgIHBhZGRpbmdUb3A6IDAsXHJcbiAgICBwYWRkaW5nQm90dG9tOiAwLFxyXG4gICAgJyZbcmVhZG9ubHldJzoge1xyXG4gICAgICBjdXJzb3I6ICdkZWZhdWx0JyxcclxuICAgIH0sXHJcbiAgfSxcclxuICAnJiAuTXVpSW5wdXRCYXNlLWlucHV0QWRvcm5lZFN0YXJ0Jzoge1xyXG4gICAgcGFkZGluZ0xlZnQ6IDAsXHJcbiAgfSxcclxufSk7XHJcblxyXG5jb25zdCBDbGVhckJ1dHRvbiA9IHN0eWxlZC5idXR0b25gXHJcbiAgZGlzcGxheTogaW5saW5lLWZsZXg7XHJcbiAgc3ZnIHtcclxuICAgIGNvbG9yOiAjYmJiO1xyXG4gIH1cclxuYDtcclxuXHJcbmNvbnN0IFNlYXJjaEJ1dHRvbiA9IHN0eWxlZC5idXR0b248eyBpc0xvYWRpbmc/OiBib29sZWFuIH0+YFxyXG4gIHBvc2l0aW9uOiByZWxhdGl2ZTtcclxuICBkaXNwbGF5OiBmbGV4O1xyXG4gIGFsaWduLWl0ZW1zOiBjZW50ZXI7XHJcbiAganVzdGlmeS1jb250ZW50OiBjZW50ZXI7XHJcbiAgYm9yZGVyLXJhZGl1czogMy4ycmVtO1xyXG4gIHdpZHRoOiAzLjJyZW07XHJcbiAgaGVpZ2h0OiAzLjJyZW07XHJcbiAgbWFyZ2luLWxlZnQ6IDA7XHJcbiAgJjpob3ZlcixcclxuICAmOmZvY3VzIHtcclxuICAgIGJhY2tncm91bmQtY29sb3I6IHJnYmEoMCwgMCwgMCwgMC4wNSk7XHJcbiAgfVxyXG4gICY6YWN0aXZlIHtcclxuICAgIGJhY2tncm91bmQtY29sb3I6IHJnYmEoMCwgMCwgMCwgMC4xKTtcclxuICB9XHJcbiAgJiA+IHN2ZyB7XHJcbiAgICBjb2xvcjogJHsoeyBpc0xvYWRpbmcgfSkgPT4gKGlzTG9hZGluZyA/ICdyZ2JhKDAsMCwwLCAwLjEpJyA6ICcjMDAwJyl9O1xyXG4gIH1cclxuYDtcclxuIl19 */",
  toString: _EMOTION_STRINGIFIED_CSS_ERROR__
});
var SearchButton = (0, _base["default"])("button", process.env.NODE_ENV === "production" ? {
  target: "e1s91y4f0"
} : {
  target: "e1s91y4f0",
  label: "SearchButton"
})("position:relative;display:flex;align-items:center;justify-content:center;border-radius:3.2rem;width:3.2rem;height:3.2rem;margin-left:0;&:hover,&:focus{background-color:rgba(0, 0, 0, 0.05);}&:active{background-color:rgba(0, 0, 0, 0.1);}&>svg{color:", function (_ref2) {
  var isLoading = _ref2.isLoading;
  return isLoading ? 'rgba(0,0,0, 0.1)' : '#000';
}, ";}" + (process.env.NODE_ENV === "production" ? "" : "/*# sourceMappingURL=data:application/json;charset=utf-8;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbIi4uLy4uLy4uL3NyYy9jb21wb25lbnRzL1RleHRGaWVsZC9UZXh0RmllbGQudHN4Il0sIm5hbWVzIjpbXSwibWFwcGluZ3MiOiJBQXVLMkQiLCJmaWxlIjoiLi4vLi4vLi4vc3JjL2NvbXBvbmVudHMvVGV4dEZpZWxkL1RleHRGaWVsZC50c3giLCJzb3VyY2VzQ29udGVudCI6WyJpbXBvcnQgUmVhY3QsIHsgdXNlTWVtbyB9IGZyb20gJ3JlYWN0JztcclxuaW1wb3J0IGNuIGZyb20gJ2NsYXNzbmFtZXMnO1xyXG5pbXBvcnQgTXVpVGV4dEZpZWxkLCB7IFRleHRGaWVsZFByb3BzIH0gZnJvbSAnQG11aS9tYXRlcmlhbC9UZXh0RmllbGQnO1xyXG5pbXBvcnQgc3R5bGVkIGZyb20gJ0BlbW90aW9uL3N0eWxlZCc7XHJcbmltcG9ydCB7IG5hbm9pZCB9IGZyb20gJ25hbm9pZCc7XHJcbmltcG9ydCBJbnB1dEFkb3JubWVudCBmcm9tICdAbXVpL21hdGVyaWFsL0lucHV0QWRvcm5tZW50JztcclxuaW1wb3J0IFNlYXJjaEljb24gZnJvbSAnLi9TZWFyY2hJY29uJztcclxuaW1wb3J0IENsZWFySWNvbiBmcm9tICdAbXVpL2ljb25zLW1hdGVyaWFsL0NsZWFyJztcclxuaW1wb3J0IGNvbG9ycyBmcm9tICcuLi8uLi90aGVtZS9jb2xvcnMnO1xyXG5pbXBvcnQgZGVlcG1lcmdlIGZyb20gJ2RlZXBtZXJnZSc7XHJcbmltcG9ydCB7IHN0eWxlZCBhcyBtdWlTdHlsZWQgfSBmcm9tICdAbXVpL21hdGVyaWFsL3N0eWxlcyc7XHJcbmltcG9ydCBPdmVybGF5U3Bpbm5lciBmcm9tICcuLi9PdmVybGF5U3Bpbm5lcic7XHJcbmltcG9ydCBJbnB1dFdyYXBwZXIgZnJvbSAnLi4vSW5wdXRXcmFwcGVyJztcclxuXHJcbnR5cGUgU2VhcmNoUHJvcHMgPSB7XHJcbiAgaXNMb2FkaW5nPzogYm9vbGVhbjtcclxuICBvbkNsZWFyPzogKCkgPT4gdm9pZDtcclxuICBvblNlYXJjaENsaWNrPzogKCkgPT4gdm9pZDtcclxufTtcclxuXHJcbnR5cGUgUHJvcHMgPSB7XHJcbiAgd2lkdGg/OiBzdHJpbmcgfCBudW1iZXI7XHJcbiAgZnVsbFdpZHRoPzogYm9vbGVhbjtcclxuICBsYWJlbEFsaWduPzogJ2xlZnQnIHwgJ3JpZ2h0JztcclxuICBsYWJlbFdpZHRoPzogbnVtYmVyIHwgc3RyaW5nO1xyXG4gIHJlYWRPbmx5PzogYm9vbGVhbjtcclxuICBXcmFwcGVyUHJvcHM/OiBSZWNvcmQ8c3RyaW5nLCBhbnk+O1xyXG4gIFNlYXJjaFByb3BzPzogU2VhcmNoUHJvcHM7XHJcbn07XHJcbmV4cG9ydCB0eXBlIFRUZXh0RmllbGRQcm9wcyA9IFByb3BzICYgT21pdDxUZXh0RmllbGRQcm9wcywga2V5b2YgUHJvcHM+O1xyXG5cclxuZnVuY3Rpb24gVGV4dEZpZWxkKHtcclxuICB0eXBlLFxyXG4gIGxhYmVsLFxyXG4gIHZhbHVlLFxyXG4gIG9uQ2hhbmdlLFxyXG4gIGxhYmVsQWxpZ24sXHJcbiAgbGFiZWxXaWR0aCxcclxuICBXcmFwcGVyUHJvcHMgPSB7fSxcclxuXHJcbiAgcmVxdWlyZWQsXHJcbiAgZGlzYWJsZWQsXHJcbiAgcmVhZE9ubHkgPSBmYWxzZSxcclxuICBlcnJvcixcclxuXHJcbiAgZnVsbFdpZHRoLFxyXG4gIHdpZHRoLFxyXG4gIFNlYXJjaFByb3BzLFxyXG4gIHN4ID0ge30sXHJcbiAgLi4ucmVzdFxyXG59OiBUVGV4dEZpZWxkUHJvcHMpIHtcclxuICBjb25zdCBoYXNoID0gdXNlTWVtbygoKSA9PiBuYW5vaWQoNiksIFtdKTtcclxuXHJcbiAgY29uc3QgY3VzdG9tU3R5bGVEZWYgPSB1c2VNZW1vKFxyXG4gICAgKCkgPT4gZGVlcG1lcmdlPFRUZXh0RmllbGRQcm9wc1snc3gnXT4oeyB3aWR0aDogd2lkdGggfHwgJzI0cmVtJyB9LCBzeCksXHJcbiAgICBbd2lkdGgsIHN4XSxcclxuICApO1xyXG5cclxuICBjb25zdCB3cmFwcGVyQ2xhc3NOYW1lID0gY24oJ2Nuc3VpLXRleHRmaWVsZCcsIFdyYXBwZXJQcm9wc1snY2xhc3NOYW1lJ10pO1xyXG5cclxuICByZXR1cm4gKFxyXG4gICAgPElucHV0V3JhcHBlclxyXG4gICAgICBodG1sRm9yPXtoYXNofVxyXG4gICAgICBsYWJlbD17bGFiZWx9XHJcbiAgICAgIGxhYmVsQWxpZ249e2xhYmVsQWxpZ259XHJcbiAgICAgIGxhYmVsV2lkdGg9e2xhYmVsV2lkdGh9XHJcbiAgICAgIHJlcXVpcmVkPXtyZXF1aXJlZH1cclxuICAgICAgZXJyb3I9e2Vycm9yfVxyXG4gICAgICBjbGFzc05hbWU9e3dyYXBwZXJDbGFzc05hbWV9XHJcbiAgICAgIGZ1bGxXaWR0aD17ZnVsbFdpZHRofVxyXG4gICAgICB7Li4uV3JhcHBlclByb3BzfVxyXG4gICAgPlxyXG4gICAgICA8U3R5bGVkVGV4dEZpZWxkXHJcbiAgICAgICAgc3g9e2N1c3RvbVN0eWxlRGVmfVxyXG4gICAgICAgIHZhcmlhbnQ9XCJzdGFuZGFyZFwiXHJcbiAgICAgICAgdHlwZT17dHlwZX1cclxuICAgICAgICBlcnJvcj17ZXJyb3J9XHJcbiAgICAgICAgbXVsdGlsaW5lPXtmYWxzZX1cclxuICAgICAgICBzZWxlY3Q9e2ZhbHNlfVxyXG4gICAgICAgIHJlcXVpcmVkPXtyZXF1aXJlZH1cclxuICAgICAgICB2YWx1ZT17dmFsdWV9XHJcbiAgICAgICAgZGlzYWJsZWQ9e2Rpc2FibGVkIHx8IEJvb2xlYW4oU2VhcmNoUHJvcHM/LmlzTG9hZGluZyl9XHJcbiAgICAgICAgb25DaGFuZ2U9e29uQ2hhbmdlfVxyXG4gICAgICAgIGlucHV0UHJvcHM9e3sgaWQ6IGhhc2ggfX1cclxuICAgICAgICBJbnB1dFByb3BzPXt7XHJcbiAgICAgICAgICByZWFkT25seSxcclxuICAgICAgICAgIC4uLih0eXBlID09PSAnc2VhcmNoJyAmJiB7XHJcbiAgICAgICAgICAgIGVuZEFkb3JubWVudDogKFxyXG4gICAgICAgICAgICAgIDxJbnB1dEFkb3JubWVudCBwb3NpdGlvbj1cImVuZFwiPlxyXG4gICAgICAgICAgICAgICAge3ZhbHVlICYmICFkaXNhYmxlZCAmJiAoXHJcbiAgICAgICAgICAgICAgICAgIDxDbGVhckJ1dHRvbiB0eXBlPVwiYnV0dG9uXCIgdGFiSW5kZXg9ey0xfSBvbkNsaWNrPXtTZWFyY2hQcm9wcz8ub25DbGVhcn0+XHJcbiAgICAgICAgICAgICAgICAgICAgPENsZWFySWNvbiBzeD17eyBmb250U2l6ZTogJzEuNXJlbScgfX0gLz5cclxuICAgICAgICAgICAgICAgICAgPC9DbGVhckJ1dHRvbj5cclxuICAgICAgICAgICAgICAgICl9XHJcbiAgICAgICAgICAgICAgICA8U2VhcmNoQnV0dG9uXHJcbiAgICAgICAgICAgICAgICAgIHR5cGU9XCJidXR0b25cIlxyXG4gICAgICAgICAgICAgICAgICB0YWJJbmRleD17LTF9XHJcbiAgICAgICAgICAgICAgICAgIGlzTG9hZGluZz17U2VhcmNoUHJvcHM/LmlzTG9hZGluZyA/PyBmYWxzZX1cclxuICAgICAgICAgICAgICAgICAgb25DbGljaz17IWRpc2FibGVkID8gU2VhcmNoUHJvcHM/Lm9uU2VhcmNoQ2xpY2sgOiB1bmRlZmluZWR9XHJcbiAgICAgICAgICAgICAgICA+XHJcbiAgICAgICAgICAgICAgICAgIHtTZWFyY2hQcm9wcz8uaXNMb2FkaW5nICYmIDxPdmVybGF5U3Bpbm5lciBjb2xvcj1cInByaW1hcnlcIiBzaXplPVwiMS41cmVtXCIgLz59XHJcbiAgICAgICAgICAgICAgICAgIDxTZWFyY2hJY29uIHdpZHRoPVwiMS41cmVtXCIgaGVpZ2h0PVwiMS41cmVtXCIgLz5cclxuICAgICAgICAgICAgICAgIDwvU2VhcmNoQnV0dG9uPlxyXG4gICAgICAgICAgICAgIDwvSW5wdXRBZG9ybm1lbnQ+XHJcbiAgICAgICAgICAgICksXHJcbiAgICAgICAgICB9KSxcclxuICAgICAgICB9fVxyXG4gICAgICAgIHsuLi5yZXN0fVxyXG4gICAgICAvPlxyXG4gICAgPC9JbnB1dFdyYXBwZXI+XHJcbiAgKTtcclxufVxyXG5cclxuZXhwb3J0IGRlZmF1bHQgVGV4dEZpZWxkO1xyXG5cclxuY29uc3QgU3R5bGVkVGV4dEZpZWxkID0gbXVpU3R5bGVkKE11aVRleHRGaWVsZCkoe1xyXG4gIGJhY2tncm91bmRDb2xvcjogJ2luaGVyaXQnLFxyXG4gICcmIC5NdWlJbnB1dC1yb290Jzoge1xyXG4gICAgZm9udFNpemU6ICcxLjRyZW0nLFxyXG4gICAgJyY6YmVmb3JlLCAmOmFmdGVyJzoge1xyXG4gICAgICBib3JkZXJCb3R0b206ICcwICFpbXBvcnRhbnQnLFxyXG4gICAgfSxcclxuICAgICcmLk11aS1kaXNhYmxlZCc6IHtcclxuICAgICAgb3BhY2l0eTogMC43NSxcclxuICAgICAgY3Vyc29yOiAnbm90LWFsbG93ZWQnLFxyXG4gICAgICAnJiBbdHlwZT1cImJ1dHRvblwiXSc6IHtcclxuICAgICAgICBjdXJzb3I6ICdub3QtYWxsb3dlZCcsXHJcbiAgICAgICAgdXNlclNlbGVjdDogJ25vbmUnLFxyXG4gICAgICAgIHpJbmRleDogJy0xJyxcclxuICAgICAgfSxcclxuICAgIH0sXHJcbiAgfSxcclxuICAnJiAuTXVpRm9ybUhlbHBlclRleHQtcm9vdCc6IHtcclxuICAgIGZvbnRTaXplOiAnMS4ycmVtJyxcclxuICAgIG1hcmdpbjogJzAuNXJlbSAwLjI1cmVtIDAnLFxyXG4gICAgZGlzcGxheTogJ2Jsb2NrJyxcclxuICAgIG92ZXJmbG93OiAnaGlkZGVuJyxcclxuICAgIHRleHRPdmVyZmxvdzogJ2VsbGlwc2lzJyxcclxuICAgICcmLk11aS1lcnJvcic6IHtcclxuICAgICAgY29sb3I6IGNvbG9yc1snZGFuZ2VyLTYwMCddLFxyXG4gICAgfSxcclxuICB9LFxyXG4gICcmIC5NdWlJbnB1dC11bmRlcmxpbmUnOiB7XHJcbiAgICBib3JkZXJCb3R0b206ICcxcHggc29saWQgcmdiYSgwLDAsMCwwLjIpJyxcclxuICB9LFxyXG4gICcmIC5NdWlJbnB1dEJhc2UtaW5wdXQnOiB7XHJcbiAgICBoZWlnaHQ6ICczLjRyZW0nLFxyXG4gICAgbGluZUhlaWdodDogJzMuNHJlbScsXHJcbiAgICBiYWNrZ3JvdW5kQ29sb3I6ICdpbmhlcml0JyxcclxuICAgIHBhZGRpbmdUb3A6IDAsXHJcbiAgICBwYWRkaW5nQm90dG9tOiAwLFxyXG4gICAgJyZbcmVhZG9ubHldJzoge1xyXG4gICAgICBjdXJzb3I6ICdkZWZhdWx0JyxcclxuICAgIH0sXHJcbiAgfSxcclxuICAnJiAuTXVpSW5wdXRCYXNlLWlucHV0QWRvcm5lZFN0YXJ0Jzoge1xyXG4gICAgcGFkZGluZ0xlZnQ6IDAsXHJcbiAgfSxcclxufSk7XHJcblxyXG5jb25zdCBDbGVhckJ1dHRvbiA9IHN0eWxlZC5idXR0b25gXHJcbiAgZGlzcGxheTogaW5saW5lLWZsZXg7XHJcbiAgc3ZnIHtcclxuICAgIGNvbG9yOiAjYmJiO1xyXG4gIH1cclxuYDtcclxuXHJcbmNvbnN0IFNlYXJjaEJ1dHRvbiA9IHN0eWxlZC5idXR0b248eyBpc0xvYWRpbmc/OiBib29sZWFuIH0+YFxyXG4gIHBvc2l0aW9uOiByZWxhdGl2ZTtcclxuICBkaXNwbGF5OiBmbGV4O1xyXG4gIGFsaWduLWl0ZW1zOiBjZW50ZXI7XHJcbiAganVzdGlmeS1jb250ZW50OiBjZW50ZXI7XHJcbiAgYm9yZGVyLXJhZGl1czogMy4ycmVtO1xyXG4gIHdpZHRoOiAzLjJyZW07XHJcbiAgaGVpZ2h0OiAzLjJyZW07XHJcbiAgbWFyZ2luLWxlZnQ6IDA7XHJcbiAgJjpob3ZlcixcclxuICAmOmZvY3VzIHtcclxuICAgIGJhY2tncm91bmQtY29sb3I6IHJnYmEoMCwgMCwgMCwgMC4wNSk7XHJcbiAgfVxyXG4gICY6YWN0aXZlIHtcclxuICAgIGJhY2tncm91bmQtY29sb3I6IHJnYmEoMCwgMCwgMCwgMC4xKTtcclxuICB9XHJcbiAgJiA+IHN2ZyB7XHJcbiAgICBjb2xvcjogJHsoeyBpc0xvYWRpbmcgfSkgPT4gKGlzTG9hZGluZyA/ICdyZ2JhKDAsMCwwLCAwLjEpJyA6ICcjMDAwJyl9O1xyXG4gIH1cclxuYDtcclxuIl19 */"));