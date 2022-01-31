var _interopRequireDefault = require("@babel/runtime/helpers/interopRequireDefault");

Object.defineProperty(exports, "__esModule", {
  value: true
});
exports["default"] = void 0;

var _extends2 = _interopRequireDefault(require("@babel/runtime/helpers/extends"));

var _objectWithoutProperties2 = _interopRequireDefault(require("@babel/runtime/helpers/objectWithoutProperties"));

var _base = _interopRequireDefault(require("@emotion/styled/base"));

var _react = _interopRequireDefault(require("react"));

var _Autocomplete = _interopRequireDefault(require("@mui/material/Autocomplete"));

var _TextField = _interopRequireDefault(require("../TextField"));

var _parse = _interopRequireDefault(require("autosuggest-highlight/parse"));

var _match = _interopRequireDefault(require("autosuggest-highlight/match"));

var _styles = require("@mui/material/styles");

var _colors = _interopRequireDefault(require("../../theme/colors"));

var _classnames = _interopRequireDefault(require("classnames"));

var _excluded = ["label", "labelAlign", "labelWidth", "required", "error", "value", "onChange", "WrapperProps", "TextFieldProps", "className"],
    _excluded2 = ["inputValue", "selected"];

function checkIsOptionEqualToValue(option, selected) {
  return option.value === selected.value;
}

function AutoComplete(_ref) {
  var label = _ref.label,
      labelAlign = _ref.labelAlign,
      labelWidth = _ref.labelWidth,
      required = _ref.required,
      error = _ref.error,
      value = _ref.value,
      onChange = _ref.onChange,
      _ref$WrapperProps = _ref.WrapperProps,
      WrapperProps = _ref$WrapperProps === void 0 ? {} : _ref$WrapperProps,
      _ref$TextFieldProps = _ref.TextFieldProps,
      TextFieldProps = _ref$TextFieldProps === void 0 ? undefined : _ref$TextFieldProps,
      className = _ref.className,
      rest = (0, _objectWithoutProperties2["default"])(_ref, _excluded);
  return /*#__PURE__*/_react["default"].createElement(_Autocomplete["default"], (0, _extends2["default"])({
    value: value,
    onChange: onChange,
    isOptionEqualToValue: checkIsOptionEqualToValue,
    className: (0, _classnames["default"])('cnsui-autocomplete', className, WrapperProps['className']),
    autoSelect: false,
    autoHighlight: true,
    clearOnBlur: false,
    renderInput: function renderInput(params) {
      return /*#__PURE__*/_react["default"].createElement(StyledTextField, (0, _extends2["default"])({
        label: label,
        labelAlign: labelAlign,
        labelWidth: labelWidth,
        required: required,
        error: error
      }, params, TextFieldProps));
    },
    renderOption: function renderOption(props, option, _ref2) {
      var inputValue = _ref2.inputValue,
          selected = _ref2.selected,
          rest = (0, _objectWithoutProperties2["default"])(_ref2, _excluded2);
      var matches = (0, _match["default"])(option.label, inputValue);
      var parts = (0, _parse["default"])(option.label, matches);
      return /*#__PURE__*/_react["default"].createElement(MenuItem, (0, _extends2["default"])({
        selected: selected
      }, props), /*#__PURE__*/_react["default"].createElement("div", null, parts.map(function (part, index) {
        return /*#__PURE__*/_react["default"].createElement("span", {
          key: index,
          style: {
            fontWeight: part.highlight ? 700 : 400
          }
        }, part.text);
      })));
    }
  }, rest));
}

var _default = AutoComplete;
exports["default"] = _default;
var StyledTextField = (0, _styles.styled)(_TextField["default"])({
  '& .MuiButtonBase-root': {
    minWidth: 'unset',
    height: 'inherit',
    padding: '0.5rem',
    borderRadius: '50%'
  },
  '& .MuiInput-input.MuiInputBase-input': {
    padding: '1rem 1.4rem'
  },
  '& .MuiAutocomplete-clearIndicator': {
    color: '#aaa'
  }
});
var MenuItem = (0, _base["default"])("li", process.env.NODE_ENV === "production" ? {
  target: "e172vte40"
} : {
  target: "e172vte40",
  label: "MenuItem"
})("font-size:1.4rem;background-color:", function (_ref3) {
  var selected = _ref3.selected;
  return selected ? _colors["default"]['secondary-500'] : 'inherit';
}, ";&:focus{background-color:", _colors["default"]['gray-100'], ";}" + (process.env.NODE_ENV === "production" ? "" : "/*# sourceMappingURL=data:application/json;charset=utf-8;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbIi4uLy4uLy4uL3NyYy9jb21wb25lbnRzL0F1dG9Db21wbGV0ZS9BdXRvQ29tcGxldGUudHN4Il0sIm5hbWVzIjpbXSwibWFwcGluZ3MiOiJBQW9HaUQiLCJmaWxlIjoiLi4vLi4vLi4vc3JjL2NvbXBvbmVudHMvQXV0b0NvbXBsZXRlL0F1dG9Db21wbGV0ZS50c3giLCJzb3VyY2VzQ29udGVudCI6WyJpbXBvcnQgUmVhY3QgZnJvbSAncmVhY3QnO1xyXG5cclxuaW1wb3J0IE11aUF1dG9Db21wbGV0ZSwgeyBBdXRvY29tcGxldGVQcm9wcyB9IGZyb20gJ0BtdWkvbWF0ZXJpYWwvQXV0b2NvbXBsZXRlJztcclxuaW1wb3J0IFRleHRGaWVsZCwgeyBUZXh0RmllbGRQcm9wcyB9IGZyb20gJy4uL1RleHRGaWVsZCc7XHJcblxyXG5pbXBvcnQgcGFyc2UgZnJvbSAnYXV0b3N1Z2dlc3QtaGlnaGxpZ2h0L3BhcnNlJztcclxuaW1wb3J0IG1hdGNoIGZyb20gJ2F1dG9zdWdnZXN0LWhpZ2hsaWdodC9tYXRjaCc7XHJcblxyXG5pbXBvcnQgc3R5bGVkIGZyb20gJ0BlbW90aW9uL3N0eWxlZCc7XHJcbmltcG9ydCB7IHN0eWxlZCBhcyBNdWlTdHlsZWQgfSBmcm9tICdAbXVpL21hdGVyaWFsL3N0eWxlcyc7XHJcbmltcG9ydCBjb2xvcnMgZnJvbSAnLi4vLi4vdGhlbWUvY29sb3JzJztcclxuaW1wb3J0IGNuIGZyb20gJ2NsYXNzbmFtZXMnO1xyXG5cclxudHlwZSBUT3B0aW9uID0geyB2YWx1ZTogc3RyaW5nOyBsYWJlbDogc3RyaW5nIH07XHJcbnR5cGUgUHJvcHMgPSB7XHJcbiAgbGFiZWw/OiBzdHJpbmc7XHJcbiAgbGFiZWxBbGlnbj86ICdsZWZ0JyB8ICdyaWdodCc7XHJcbiAgbGFiZWxXaWR0aD86IG51bWJlciB8IHN0cmluZztcclxuICByZXF1aXJlZD86IGJvb2xlYW47XHJcbiAgZXJyb3I/OiBib29sZWFuO1xyXG4gIFdyYXBwZXJQcm9wcz86IFJlY29yZDxzdHJpbmcsIGFueT47XHJcbiAgVGV4dEZpZWxkUHJvcHM/OiBPbWl0PFRleHRGaWVsZFByb3BzLCAnbGFiZWwnIHwgJ2xhYmVsQWxpZ24nIHwgJ2xhYmVsV2lkdGgnIHwgJ3JlcXVpcmVkJyB8ICdlcnJvcic+O1xyXG59O1xyXG5cclxuZXhwb3J0IHR5cGUgVEF1dG9Db21wbGV0ZVByb3BzID0gUHJvcHMgJlxyXG4gIE9taXQ8XHJcbiAgICBBdXRvY29tcGxldGVQcm9wczxUT3B0aW9uLCBib29sZWFuIHwgdW5kZWZpbmVkLCBib29sZWFuIHwgdW5kZWZpbmVkLCBib29sZWFuIHwgdW5kZWZpbmVkLCBhbnk+LFxyXG4gICAga2V5b2YgUHJvcHMgfCAncmVuZGVySW5wdXQnIHwgJ3JlbmRlck9wdGlvbidcclxuICA+O1xyXG5cclxuZnVuY3Rpb24gY2hlY2tJc09wdGlvbkVxdWFsVG9WYWx1ZShvcHRpb246IFRPcHRpb24sIHNlbGVjdGVkOiBUT3B0aW9uKSB7XHJcbiAgcmV0dXJuIG9wdGlvbi52YWx1ZSA9PT0gc2VsZWN0ZWQudmFsdWU7XHJcbn1cclxuXHJcbmZ1bmN0aW9uIEF1dG9Db21wbGV0ZSh7XHJcbiAgbGFiZWwsXHJcbiAgbGFiZWxBbGlnbixcclxuICBsYWJlbFdpZHRoLFxyXG4gIHJlcXVpcmVkLFxyXG4gIGVycm9yLFxyXG4gIHZhbHVlLFxyXG4gIG9uQ2hhbmdlLFxyXG4gIFdyYXBwZXJQcm9wcyA9IHt9LFxyXG4gIFRleHRGaWVsZFByb3BzID0gdW5kZWZpbmVkLFxyXG4gIGNsYXNzTmFtZSxcclxuICAuLi5yZXN0XHJcbn06IFRBdXRvQ29tcGxldGVQcm9wcykge1xyXG4gIHJldHVybiAoXHJcbiAgICA8TXVpQXV0b0NvbXBsZXRlXHJcbiAgICAgIHZhbHVlPXt2YWx1ZX1cclxuICAgICAgb25DaGFuZ2U9e29uQ2hhbmdlfVxyXG4gICAgICBpc09wdGlvbkVxdWFsVG9WYWx1ZT17Y2hlY2tJc09wdGlvbkVxdWFsVG9WYWx1ZX1cclxuICAgICAgY2xhc3NOYW1lPXtjbignY25zdWktYXV0b2NvbXBsZXRlJywgY2xhc3NOYW1lLCBXcmFwcGVyUHJvcHNbJ2NsYXNzTmFtZSddKX1cclxuICAgICAgYXV0b1NlbGVjdD17ZmFsc2V9XHJcbiAgICAgIGF1dG9IaWdobGlnaHQ9e3RydWV9XHJcbiAgICAgIGNsZWFyT25CbHVyPXtmYWxzZX1cclxuICAgICAgcmVuZGVySW5wdXQ9eyhwYXJhbXMpID0+IChcclxuICAgICAgICA8U3R5bGVkVGV4dEZpZWxkXHJcbiAgICAgICAgICB7Li4ueyBsYWJlbCwgbGFiZWxBbGlnbiwgbGFiZWxXaWR0aCwgcmVxdWlyZWQsIGVycm9yIH19XHJcbiAgICAgICAgICB7Li4ucGFyYW1zfVxyXG4gICAgICAgICAgey4uLlRleHRGaWVsZFByb3BzfVxyXG4gICAgICAgIC8+XHJcbiAgICAgICl9XHJcbiAgICAgIHJlbmRlck9wdGlvbj17KHByb3BzLCBvcHRpb24sIHsgaW5wdXRWYWx1ZSwgc2VsZWN0ZWQsIC4uLnJlc3QgfSkgPT4ge1xyXG4gICAgICAgIGNvbnN0IG1hdGNoZXMgPSBtYXRjaChvcHRpb24ubGFiZWwsIGlucHV0VmFsdWUpO1xyXG4gICAgICAgIGNvbnN0IHBhcnRzID0gcGFyc2Uob3B0aW9uLmxhYmVsLCBtYXRjaGVzKTtcclxuICAgICAgICByZXR1cm4gKFxyXG4gICAgICAgICAgPE1lbnVJdGVtIHNlbGVjdGVkPXtzZWxlY3RlZH0gey4uLnByb3BzfT5cclxuICAgICAgICAgICAgPGRpdj5cclxuICAgICAgICAgICAgICB7cGFydHMubWFwKChwYXJ0LCBpbmRleCkgPT4gKFxyXG4gICAgICAgICAgICAgICAgPHNwYW4ga2V5PXtpbmRleH0gc3R5bGU9e3sgZm9udFdlaWdodDogcGFydC5oaWdobGlnaHQgPyA3MDAgOiA0MDAgfX0+XHJcbiAgICAgICAgICAgICAgICAgIHtwYXJ0LnRleHR9XHJcbiAgICAgICAgICAgICAgICA8L3NwYW4+XHJcbiAgICAgICAgICAgICAgKSl9XHJcbiAgICAgICAgICAgIDwvZGl2PlxyXG4gICAgICAgICAgPC9NZW51SXRlbT5cclxuICAgICAgICApO1xyXG4gICAgICB9fVxyXG4gICAgICB7Li4ucmVzdH1cclxuICAgIC8+XHJcbiAgKTtcclxufVxyXG5cclxuZXhwb3J0IGRlZmF1bHQgQXV0b0NvbXBsZXRlO1xyXG5cclxuY29uc3QgU3R5bGVkVGV4dEZpZWxkID0gTXVpU3R5bGVkKFRleHRGaWVsZCkoe1xyXG4gICcmIC5NdWlCdXR0b25CYXNlLXJvb3QnOiB7XHJcbiAgICBtaW5XaWR0aDogJ3Vuc2V0JyxcclxuICAgIGhlaWdodDogJ2luaGVyaXQnLFxyXG4gICAgcGFkZGluZzogJzAuNXJlbScsXHJcbiAgICBib3JkZXJSYWRpdXM6ICc1MCUnLFxyXG4gIH0sXHJcbiAgJyYgLk11aUlucHV0LWlucHV0Lk11aUlucHV0QmFzZS1pbnB1dCc6IHtcclxuICAgIHBhZGRpbmc6ICcxcmVtIDEuNHJlbScsXHJcbiAgfSxcclxuICAnJiAuTXVpQXV0b2NvbXBsZXRlLWNsZWFySW5kaWNhdG9yJzoge1xyXG4gICAgY29sb3I6ICcjYWFhJyxcclxuICB9LFxyXG59KTtcclxuXHJcbmNvbnN0IE1lbnVJdGVtID0gc3R5bGVkLmxpPHsgc2VsZWN0ZWQ6IGJvb2xlYW4gfT5gXHJcbiAgZm9udC1zaXplOiAxLjRyZW07XHJcbiAgYmFja2dyb3VuZC1jb2xvcjogJHsoeyBzZWxlY3RlZCB9KSA9PiAoc2VsZWN0ZWQgPyBjb2xvcnNbJ3NlY29uZGFyeS01MDAnXSA6ICdpbmhlcml0Jyl9O1xyXG4gICY6Zm9jdXMge1xyXG4gICAgYmFja2dyb3VuZC1jb2xvcjogJHtjb2xvcnNbJ2dyYXktMTAwJ119O1xyXG4gIH1cclxuYDtcclxuIl19 */"));