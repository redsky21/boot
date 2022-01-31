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

var _classnames = _interopRequireDefault(require("classnames"));

var _react = _interopRequireWildcard(require("react"));

var _DatePicker = _interopRequireDefault(require("rsuite/DatePicker"));

var _InputWrapper = _interopRequireDefault(require("../InputWrapper"));

var _excluded = ["label", "labelAlign", "labelWidth", "required", "width", "fullWidth", "error", "WrapperProps", "disablePortal", "container"];

function _getRequireWildcardCache(nodeInterop) { if (typeof WeakMap !== "function") return null; var cacheBabelInterop = new WeakMap(); var cacheNodeInterop = new WeakMap(); return (_getRequireWildcardCache = function _getRequireWildcardCache(nodeInterop) { return nodeInterop ? cacheNodeInterop : cacheBabelInterop; })(nodeInterop); }

function _interopRequireWildcard(obj, nodeInterop) { if (!nodeInterop && obj && obj.__esModule) { return obj; } if (obj === null || _typeof(obj) !== "object" && typeof obj !== "function") { return { "default": obj }; } var cache = _getRequireWildcardCache(nodeInterop); if (cache && cache.has(obj)) { return cache.get(obj); } var newObj = {}; var hasPropertyDescriptor = Object.defineProperty && Object.getOwnPropertyDescriptor; for (var key in obj) { if (key !== "default" && Object.prototype.hasOwnProperty.call(obj, key)) { var desc = hasPropertyDescriptor ? Object.getOwnPropertyDescriptor(obj, key) : null; if (desc && (desc.get || desc.set)) { Object.defineProperty(newObj, key, desc); } else { newObj[key] = obj[key]; } } } newObj["default"] = obj; if (cache) { cache.set(obj, newObj); } return newObj; }

function ownKeys(object, enumerableOnly) { var keys = Object.keys(object); if (Object.getOwnPropertySymbols) { var symbols = Object.getOwnPropertySymbols(object); enumerableOnly && (symbols = symbols.filter(function (sym) { return Object.getOwnPropertyDescriptor(object, sym).enumerable; })), keys.push.apply(keys, symbols); } return keys; }

function _objectSpread(target) { for (var i = 1; i < arguments.length; i++) { var source = null != arguments[i] ? arguments[i] : {}; i % 2 ? ownKeys(Object(source), !0).forEach(function (key) { (0, _defineProperty2["default"])(target, key, source[key]); }) : Object.getOwnPropertyDescriptors ? Object.defineProperties(target, Object.getOwnPropertyDescriptors(source)) : ownKeys(Object(source)).forEach(function (key) { Object.defineProperty(target, key, Object.getOwnPropertyDescriptor(source, key)); }); } return target; }

/** https://rsuitejs.com/components/date-picker/#%3CDatePicker%3E */
var DatePicker = /*#__PURE__*/_react["default"].forwardRef(function (_ref, ref) {
  var _inputWrapperRef$curr;

  var label = _ref.label,
      labelAlign = _ref.labelAlign,
      labelWidth = _ref.labelWidth,
      required = _ref.required,
      width = _ref.width,
      fullWidth = _ref.fullWidth,
      error = _ref.error,
      _ref$WrapperProps = _ref.WrapperProps,
      WrapperProps = _ref$WrapperProps === void 0 ? {} : _ref$WrapperProps,
      _ref$disablePortal = _ref.disablePortal,
      disablePortal = _ref$disablePortal === void 0 ? false : _ref$disablePortal,
      container = _ref.container,
      rest = (0, _objectWithoutProperties2["default"])(_ref, _excluded);
  // const hash = React.useMemo(() => nanoid(), []);
  var inputWrapperRef = (0, _react.useRef)(null);
  (0, _react.useImperativeHandle)(ref, function () {
    return inputWrapperRef.current;
  });
  WrapperProps = _objectSpread(_objectSpread({}, WrapperProps), {}, {
    className: (0, _classnames["default"])('cnsui-datepicker', WrapperProps['className'])
  });
  return /*#__PURE__*/_react["default"].createElement(StyledInputWrapper // id={hash}
  , (0, _extends2["default"])({
    label: label,
    labelAlign: labelAlign,
    labelWidth: labelWidth,
    required: required,
    error: error,
    width: width,
    fullWidth: fullWidth,
    ref: inputWrapperRef
  }, WrapperProps), /*#__PURE__*/_react["default"].createElement(DatePickerWrapper, {
    error: error
  }, /*#__PURE__*/_react["default"].createElement(_DatePicker["default"], (0, _extends2["default"])({
    container: (container !== null && container !== void 0 ? container : disablePortal) ? (_inputWrapperRef$curr = inputWrapperRef.current) !== null && _inputWrapperRef$curr !== void 0 ? _inputWrapperRef$curr : undefined : undefined
  }, rest))));
});

var DatePickerWrapper = (0, _base["default"])("div", process.env.NODE_ENV === "production" ? {
  target: "e133cm481"
} : {
  target: "e133cm481",
  label: "DatePickerWrapper"
})(function (_ref2) {
  var error = _ref2.error;
  return error && "\n      & svg {\n        fill: #DB2330 !important\n      }\n      & input {\n        color: #DB2330 !important;\n        border-bottom: 1px solid #DB2330 !important;  \n        &::placeholder {\n          color: #DB2330 !important\n        }\n      }\n  ";
}, ";" + (process.env.NODE_ENV === "production" ? "" : "/*# sourceMappingURL=data:application/json;charset=utf-8;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbIi4uLy4uLy4uL3NyYy9jb21wb25lbnRzL0RhdGVQaWNrZXIvRGF0ZVBpY2tlci50c3giXSwibmFtZXMiOltdLCJtYXBwaW5ncyI6IkFBMEV5RCIsImZpbGUiOiIuLi8uLi8uLi9zcmMvY29tcG9uZW50cy9EYXRlUGlja2VyL0RhdGVQaWNrZXIudHN4Iiwic291cmNlc0NvbnRlbnQiOlsiaW1wb3J0IHN0eWxlZCBmcm9tICdAZW1vdGlvbi9zdHlsZWQnO1xyXG5pbXBvcnQgY24gZnJvbSAnY2xhc3NuYW1lcyc7XHJcbmltcG9ydCB7IG5hbm9pZCB9IGZyb20gJ25hbm9pZCc7XHJcbmltcG9ydCBSZWFjdCwgeyBDU1NQcm9wZXJ0aWVzLCB1c2VSZWYsIHVzZUltcGVyYXRpdmVIYW5kbGUgfSBmcm9tICdyZWFjdCc7XHJcbmltcG9ydCBSU3VpdGVEYXRlUGlja2VyLCB7IERhdGVQaWNrZXJQcm9wcyB9IGZyb20gJ3JzdWl0ZS9EYXRlUGlja2VyJztcclxuaW1wb3J0IElucHV0V3JhcHBlciBmcm9tICcuLi9JbnB1dFdyYXBwZXInO1xyXG5cclxuZXhwb3J0IHR5cGUgVERhdGVQaWNrZXIgPSB7XHJcbiAgbGFiZWw/OiBzdHJpbmc7XHJcbiAgbGFiZWxBbGlnbj86ICdsZWZ0JyB8ICdyaWdodCc7XHJcbiAgbGFiZWxXaWR0aD86IG51bWJlciB8IHN0cmluZztcclxuICByZXF1aXJlZD86IGJvb2xlYW47XHJcbiAgZXJyb3I/OiBib29sZWFuO1xyXG4gIHdpZHRoPzogQ1NTUHJvcGVydGllc1snd2lkdGgnXTtcclxuICBmdWxsV2lkdGg/OiBib29sZWFuO1xyXG4gIFdyYXBwZXJQcm9wcz86IFJlY29yZDxzdHJpbmcsIGFueT47XHJcbiAgZGlzYWJsZVBvcnRhbD86IGJvb2xlYW47XHJcbn0gJiBEYXRlUGlja2VyUHJvcHM7XHJcbi8qKiBodHRwczovL3JzdWl0ZWpzLmNvbS9jb21wb25lbnRzL2RhdGUtcGlja2VyLyMlM0NEYXRlUGlja2VyJTNFICovXHJcblxyXG5jb25zdCBEYXRlUGlja2VyID0gUmVhY3QuZm9yd2FyZFJlZjxIVE1MRGl2RWxlbWVudCwgVERhdGVQaWNrZXI+KFxyXG4gIChcclxuICAgIHtcclxuICAgICAgbGFiZWwsXHJcbiAgICAgIGxhYmVsQWxpZ24sXHJcbiAgICAgIGxhYmVsV2lkdGgsXHJcbiAgICAgIHJlcXVpcmVkLFxyXG4gICAgICB3aWR0aCxcclxuICAgICAgZnVsbFdpZHRoLFxyXG4gICAgICBlcnJvcixcclxuICAgICAgV3JhcHBlclByb3BzID0ge30sXHJcbiAgICAgIGRpc2FibGVQb3J0YWwgPSBmYWxzZSxcclxuICAgICAgY29udGFpbmVyLFxyXG4gICAgICAuLi5yZXN0XHJcbiAgICB9LFxyXG4gICAgcmVmLFxyXG4gICkgPT4ge1xyXG4gICAgLy8gY29uc3QgaGFzaCA9IFJlYWN0LnVzZU1lbW8oKCkgPT4gbmFub2lkKCksIFtdKTtcclxuICAgIGNvbnN0IGlucHV0V3JhcHBlclJlZiA9IHVzZVJlZjxIVE1MRGl2RWxlbWVudD4obnVsbCk7XHJcbiAgICB1c2VJbXBlcmF0aXZlSGFuZGxlKHJlZiwgKCkgPT4ge1xyXG4gICAgICByZXR1cm4gaW5wdXRXcmFwcGVyUmVmLmN1cnJlbnQgYXMgSFRNTERpdkVsZW1lbnQ7XHJcbiAgICB9KTtcclxuXHJcbiAgICBXcmFwcGVyUHJvcHMgPSB7XHJcbiAgICAgIC4uLldyYXBwZXJQcm9wcyxcclxuICAgICAgY2xhc3NOYW1lOiBjbignY25zdWktZGF0ZXBpY2tlcicsIFdyYXBwZXJQcm9wc1snY2xhc3NOYW1lJ10pLFxyXG4gICAgfTtcclxuXHJcbiAgICByZXR1cm4gKFxyXG4gICAgICA8U3R5bGVkSW5wdXRXcmFwcGVyXHJcbiAgICAgICAgLy8gaWQ9e2hhc2h9XHJcbiAgICAgICAgbGFiZWw9e2xhYmVsfVxyXG4gICAgICAgIGxhYmVsQWxpZ249e2xhYmVsQWxpZ259XHJcbiAgICAgICAgbGFiZWxXaWR0aD17bGFiZWxXaWR0aH1cclxuICAgICAgICByZXF1aXJlZD17cmVxdWlyZWR9XHJcbiAgICAgICAgZXJyb3I9e2Vycm9yfVxyXG4gICAgICAgIHdpZHRoPXt3aWR0aH1cclxuICAgICAgICBmdWxsV2lkdGg9e2Z1bGxXaWR0aH1cclxuICAgICAgICByZWY9e2lucHV0V3JhcHBlclJlZn1cclxuICAgICAgICB7Li4uV3JhcHBlclByb3BzfVxyXG4gICAgICA+XHJcbiAgICAgICAgPERhdGVQaWNrZXJXcmFwcGVyIGVycm9yPXtlcnJvcn0+XHJcbiAgICAgICAgICA8UlN1aXRlRGF0ZVBpY2tlclxyXG4gICAgICAgICAgICBjb250YWluZXI9e1xyXG4gICAgICAgICAgICAgIGNvbnRhaW5lciA/PyBkaXNhYmxlUG9ydGFsID8gaW5wdXRXcmFwcGVyUmVmLmN1cnJlbnQgPz8gdW5kZWZpbmVkIDogdW5kZWZpbmVkXHJcbiAgICAgICAgICAgIH1cclxuICAgICAgICAgICAgey4uLnJlc3R9XHJcbiAgICAgICAgICAvPlxyXG4gICAgICAgIDwvRGF0ZVBpY2tlcldyYXBwZXI+XHJcbiAgICAgIDwvU3R5bGVkSW5wdXRXcmFwcGVyPlxyXG4gICAgKTtcclxuICB9LFxyXG4pO1xyXG5cclxuY29uc3QgRGF0ZVBpY2tlcldyYXBwZXIgPSBzdHlsZWQuZGl2PHsgZXJyb3I/OiBib29sZWFuIH0+YFxyXG4gICR7KHsgZXJyb3IgfSkgPT5cclxuICAgIGVycm9yICYmXHJcbiAgICBgXHJcbiAgICAgICYgc3ZnIHtcclxuICAgICAgICBmaWxsOiAjREIyMzMwICFpbXBvcnRhbnRcclxuICAgICAgfVxyXG4gICAgICAmIGlucHV0IHtcclxuICAgICAgICBjb2xvcjogI0RCMjMzMCAhaW1wb3J0YW50O1xyXG4gICAgICAgIGJvcmRlci1ib3R0b206IDFweCBzb2xpZCAjREIyMzMwICFpbXBvcnRhbnQ7ICBcclxuICAgICAgICAmOjpwbGFjZWhvbGRlciB7XHJcbiAgICAgICAgICBjb2xvcjogI0RCMjMzMCAhaW1wb3J0YW50XHJcbiAgICAgICAgfVxyXG4gICAgICB9XHJcbiAgYH1cclxuYDtcclxuXHJcbmV4cG9ydCBkZWZhdWx0IERhdGVQaWNrZXI7XHJcblxyXG5jb25zdCBTdHlsZWRJbnB1dFdyYXBwZXIgPSBzdHlsZWQoSW5wdXRXcmFwcGVyKTx7XHJcbiAgLy8gaWQ6IHN0cmluZztcclxuICB3aWR0aD86IFREYXRlUGlja2VyWyd3aWR0aCddO1xyXG4gIGZ1bGxXaWR0aD86IFREYXRlUGlja2VyWydmdWxsV2lkdGgnXTtcclxufT5gXHJcbiAgLyogJiMkeyh7IGlkIH0pID0+IGlkfSB7ICovXHJcbiAgZmxleC13cmFwOiBub3dyYXA7XHJcbiAgLnJzLXBpY2tlci5ycy1waWNrZXItZGF0ZSB7XHJcbiAgICBtaW4td2lkdGg6ICR7KHsgd2lkdGgsIGZ1bGxXaWR0aCB9KSA9PlxyXG4gICAgICBmdWxsV2lkdGggPyAnMTAwJScgOiB3aWR0aCA/ICh0eXBlb2Ygd2lkdGggPT09ICdudW1iZXInID8gYCR7d2lkdGh9cHhgIDogd2lkdGgpIDogJzI0cmVtJ307XHJcbiAgICBwYWRkaW5nLXJpZ2h0OiAwICFpbXBvcnRhbnQ7XHJcblxyXG4gICAgLnJzLXBpY2tlci10b2dnbGUgaW5wdXQge1xyXG4gICAgICB3aWR0aDogJHsoeyB3aWR0aCwgZnVsbFdpZHRoIH0pID0+XHJcbiAgICAgICAgZnVsbFdpZHRoID8gJzEwMCUnIDogd2lkdGggPyAodHlwZW9mIHdpZHRoID09PSAnbnVtYmVyJyA/IGAke3dpZHRofXB4YCA6IHdpZHRoKSA6ICcyNHJlbSd9O1xyXG4gICAgICBtYXJnaW4tcmlnaHQ6IDAgIWltcG9ydGFudDtcclxuICAgIH1cclxuICB9XHJcbiAgLyogfSAqL1xyXG5gO1xyXG4iXX0= */"));
var _default = DatePicker;
exports["default"] = _default;
var StyledInputWrapper = ( /*#__PURE__*/0, _base["default"])(_InputWrapper["default"], process.env.NODE_ENV === "production" ? {
  target: "e133cm480"
} : {
  target: "e133cm480",
  label: "StyledInputWrapper"
})("flex-wrap:nowrap;.rs-picker.rs-picker-date{min-width:", function (_ref3) {
  var width = _ref3.width,
      fullWidth = _ref3.fullWidth;
  return fullWidth ? '100%' : width ? typeof width === 'number' ? "".concat(width, "px") : width : '24rem';
}, ";padding-right:0!important;.rs-picker-toggle input{width:", function (_ref4) {
  var width = _ref4.width,
      fullWidth = _ref4.fullWidth;
  return fullWidth ? '100%' : width ? typeof width === 'number' ? "".concat(width, "px") : width : '24rem';
}, ";margin-right:0!important;}}" + (process.env.NODE_ENV === "production" ? "" : "/*# sourceMappingURL=data:application/json;charset=utf-8;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbIi4uLy4uLy4uL3NyYy9jb21wb25lbnRzL0RhdGVQaWNrZXIvRGF0ZVBpY2tlci50c3giXSwibmFtZXMiOltdLCJtYXBwaW5ncyI6IkFBaUdFIiwiZmlsZSI6Ii4uLy4uLy4uL3NyYy9jb21wb25lbnRzL0RhdGVQaWNrZXIvRGF0ZVBpY2tlci50c3giLCJzb3VyY2VzQ29udGVudCI6WyJpbXBvcnQgc3R5bGVkIGZyb20gJ0BlbW90aW9uL3N0eWxlZCc7XHJcbmltcG9ydCBjbiBmcm9tICdjbGFzc25hbWVzJztcclxuaW1wb3J0IHsgbmFub2lkIH0gZnJvbSAnbmFub2lkJztcclxuaW1wb3J0IFJlYWN0LCB7IENTU1Byb3BlcnRpZXMsIHVzZVJlZiwgdXNlSW1wZXJhdGl2ZUhhbmRsZSB9IGZyb20gJ3JlYWN0JztcclxuaW1wb3J0IFJTdWl0ZURhdGVQaWNrZXIsIHsgRGF0ZVBpY2tlclByb3BzIH0gZnJvbSAncnN1aXRlL0RhdGVQaWNrZXInO1xyXG5pbXBvcnQgSW5wdXRXcmFwcGVyIGZyb20gJy4uL0lucHV0V3JhcHBlcic7XHJcblxyXG5leHBvcnQgdHlwZSBURGF0ZVBpY2tlciA9IHtcclxuICBsYWJlbD86IHN0cmluZztcclxuICBsYWJlbEFsaWduPzogJ2xlZnQnIHwgJ3JpZ2h0JztcclxuICBsYWJlbFdpZHRoPzogbnVtYmVyIHwgc3RyaW5nO1xyXG4gIHJlcXVpcmVkPzogYm9vbGVhbjtcclxuICBlcnJvcj86IGJvb2xlYW47XHJcbiAgd2lkdGg/OiBDU1NQcm9wZXJ0aWVzWyd3aWR0aCddO1xyXG4gIGZ1bGxXaWR0aD86IGJvb2xlYW47XHJcbiAgV3JhcHBlclByb3BzPzogUmVjb3JkPHN0cmluZywgYW55PjtcclxuICBkaXNhYmxlUG9ydGFsPzogYm9vbGVhbjtcclxufSAmIERhdGVQaWNrZXJQcm9wcztcclxuLyoqIGh0dHBzOi8vcnN1aXRlanMuY29tL2NvbXBvbmVudHMvZGF0ZS1waWNrZXIvIyUzQ0RhdGVQaWNrZXIlM0UgKi9cclxuXHJcbmNvbnN0IERhdGVQaWNrZXIgPSBSZWFjdC5mb3J3YXJkUmVmPEhUTUxEaXZFbGVtZW50LCBURGF0ZVBpY2tlcj4oXHJcbiAgKFxyXG4gICAge1xyXG4gICAgICBsYWJlbCxcclxuICAgICAgbGFiZWxBbGlnbixcclxuICAgICAgbGFiZWxXaWR0aCxcclxuICAgICAgcmVxdWlyZWQsXHJcbiAgICAgIHdpZHRoLFxyXG4gICAgICBmdWxsV2lkdGgsXHJcbiAgICAgIGVycm9yLFxyXG4gICAgICBXcmFwcGVyUHJvcHMgPSB7fSxcclxuICAgICAgZGlzYWJsZVBvcnRhbCA9IGZhbHNlLFxyXG4gICAgICBjb250YWluZXIsXHJcbiAgICAgIC4uLnJlc3RcclxuICAgIH0sXHJcbiAgICByZWYsXHJcbiAgKSA9PiB7XHJcbiAgICAvLyBjb25zdCBoYXNoID0gUmVhY3QudXNlTWVtbygoKSA9PiBuYW5vaWQoKSwgW10pO1xyXG4gICAgY29uc3QgaW5wdXRXcmFwcGVyUmVmID0gdXNlUmVmPEhUTUxEaXZFbGVtZW50PihudWxsKTtcclxuICAgIHVzZUltcGVyYXRpdmVIYW5kbGUocmVmLCAoKSA9PiB7XHJcbiAgICAgIHJldHVybiBpbnB1dFdyYXBwZXJSZWYuY3VycmVudCBhcyBIVE1MRGl2RWxlbWVudDtcclxuICAgIH0pO1xyXG5cclxuICAgIFdyYXBwZXJQcm9wcyA9IHtcclxuICAgICAgLi4uV3JhcHBlclByb3BzLFxyXG4gICAgICBjbGFzc05hbWU6IGNuKCdjbnN1aS1kYXRlcGlja2VyJywgV3JhcHBlclByb3BzWydjbGFzc05hbWUnXSksXHJcbiAgICB9O1xyXG5cclxuICAgIHJldHVybiAoXHJcbiAgICAgIDxTdHlsZWRJbnB1dFdyYXBwZXJcclxuICAgICAgICAvLyBpZD17aGFzaH1cclxuICAgICAgICBsYWJlbD17bGFiZWx9XHJcbiAgICAgICAgbGFiZWxBbGlnbj17bGFiZWxBbGlnbn1cclxuICAgICAgICBsYWJlbFdpZHRoPXtsYWJlbFdpZHRofVxyXG4gICAgICAgIHJlcXVpcmVkPXtyZXF1aXJlZH1cclxuICAgICAgICBlcnJvcj17ZXJyb3J9XHJcbiAgICAgICAgd2lkdGg9e3dpZHRofVxyXG4gICAgICAgIGZ1bGxXaWR0aD17ZnVsbFdpZHRofVxyXG4gICAgICAgIHJlZj17aW5wdXRXcmFwcGVyUmVmfVxyXG4gICAgICAgIHsuLi5XcmFwcGVyUHJvcHN9XHJcbiAgICAgID5cclxuICAgICAgICA8RGF0ZVBpY2tlcldyYXBwZXIgZXJyb3I9e2Vycm9yfT5cclxuICAgICAgICAgIDxSU3VpdGVEYXRlUGlja2VyXHJcbiAgICAgICAgICAgIGNvbnRhaW5lcj17XHJcbiAgICAgICAgICAgICAgY29udGFpbmVyID8/IGRpc2FibGVQb3J0YWwgPyBpbnB1dFdyYXBwZXJSZWYuY3VycmVudCA/PyB1bmRlZmluZWQgOiB1bmRlZmluZWRcclxuICAgICAgICAgICAgfVxyXG4gICAgICAgICAgICB7Li4ucmVzdH1cclxuICAgICAgICAgIC8+XHJcbiAgICAgICAgPC9EYXRlUGlja2VyV3JhcHBlcj5cclxuICAgICAgPC9TdHlsZWRJbnB1dFdyYXBwZXI+XHJcbiAgICApO1xyXG4gIH0sXHJcbik7XHJcblxyXG5jb25zdCBEYXRlUGlja2VyV3JhcHBlciA9IHN0eWxlZC5kaXY8eyBlcnJvcj86IGJvb2xlYW4gfT5gXHJcbiAgJHsoeyBlcnJvciB9KSA9PlxyXG4gICAgZXJyb3IgJiZcclxuICAgIGBcclxuICAgICAgJiBzdmcge1xyXG4gICAgICAgIGZpbGw6ICNEQjIzMzAgIWltcG9ydGFudFxyXG4gICAgICB9XHJcbiAgICAgICYgaW5wdXQge1xyXG4gICAgICAgIGNvbG9yOiAjREIyMzMwICFpbXBvcnRhbnQ7XHJcbiAgICAgICAgYm9yZGVyLWJvdHRvbTogMXB4IHNvbGlkICNEQjIzMzAgIWltcG9ydGFudDsgIFxyXG4gICAgICAgICY6OnBsYWNlaG9sZGVyIHtcclxuICAgICAgICAgIGNvbG9yOiAjREIyMzMwICFpbXBvcnRhbnRcclxuICAgICAgICB9XHJcbiAgICAgIH1cclxuICBgfVxyXG5gO1xyXG5cclxuZXhwb3J0IGRlZmF1bHQgRGF0ZVBpY2tlcjtcclxuXHJcbmNvbnN0IFN0eWxlZElucHV0V3JhcHBlciA9IHN0eWxlZChJbnB1dFdyYXBwZXIpPHtcclxuICAvLyBpZDogc3RyaW5nO1xyXG4gIHdpZHRoPzogVERhdGVQaWNrZXJbJ3dpZHRoJ107XHJcbiAgZnVsbFdpZHRoPzogVERhdGVQaWNrZXJbJ2Z1bGxXaWR0aCddO1xyXG59PmBcclxuICAvKiAmIyR7KHsgaWQgfSkgPT4gaWR9IHsgKi9cclxuICBmbGV4LXdyYXA6IG5vd3JhcDtcclxuICAucnMtcGlja2VyLnJzLXBpY2tlci1kYXRlIHtcclxuICAgIG1pbi13aWR0aDogJHsoeyB3aWR0aCwgZnVsbFdpZHRoIH0pID0+XHJcbiAgICAgIGZ1bGxXaWR0aCA/ICcxMDAlJyA6IHdpZHRoID8gKHR5cGVvZiB3aWR0aCA9PT0gJ251bWJlcicgPyBgJHt3aWR0aH1weGAgOiB3aWR0aCkgOiAnMjRyZW0nfTtcclxuICAgIHBhZGRpbmctcmlnaHQ6IDAgIWltcG9ydGFudDtcclxuXHJcbiAgICAucnMtcGlja2VyLXRvZ2dsZSBpbnB1dCB7XHJcbiAgICAgIHdpZHRoOiAkeyh7IHdpZHRoLCBmdWxsV2lkdGggfSkgPT5cclxuICAgICAgICBmdWxsV2lkdGggPyAnMTAwJScgOiB3aWR0aCA/ICh0eXBlb2Ygd2lkdGggPT09ICdudW1iZXInID8gYCR7d2lkdGh9cHhgIDogd2lkdGgpIDogJzI0cmVtJ307XHJcbiAgICAgIG1hcmdpbi1yaWdodDogMCAhaW1wb3J0YW50O1xyXG4gICAgfVxyXG4gIH1cclxuICAvKiB9ICovXHJcbmA7XHJcbiJdfQ== */"));