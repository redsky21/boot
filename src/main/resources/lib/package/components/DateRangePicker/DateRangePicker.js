var _interopRequireDefault = require("@babel/runtime/helpers/interopRequireDefault");

Object.defineProperty(exports, "__esModule", {
  value: true
});
exports["default"] = void 0;

var _extends2 = _interopRequireDefault(require("@babel/runtime/helpers/extends"));

var _defineProperty2 = _interopRequireDefault(require("@babel/runtime/helpers/defineProperty"));

var _objectWithoutProperties2 = _interopRequireDefault(require("@babel/runtime/helpers/objectWithoutProperties"));

var _base = _interopRequireDefault(require("@emotion/styled/base"));

var _classnames = _interopRequireDefault(require("classnames"));

var _react = _interopRequireDefault(require("react"));

var _DateRangePicker = _interopRequireDefault(require("rsuite/DateRangePicker"));

var _InputWrapper = _interopRequireDefault(require("../InputWrapper"));

var _excluded = ["label", "labelAlign", "labelWidth", "required", "error", "WrapperProps"];

function ownKeys(object, enumerableOnly) { var keys = Object.keys(object); if (Object.getOwnPropertySymbols) { var symbols = Object.getOwnPropertySymbols(object); enumerableOnly && (symbols = symbols.filter(function (sym) { return Object.getOwnPropertyDescriptor(object, sym).enumerable; })), keys.push.apply(keys, symbols); } return keys; }

function _objectSpread(target) { for (var i = 1; i < arguments.length; i++) { var source = null != arguments[i] ? arguments[i] : {}; i % 2 ? ownKeys(Object(source), !0).forEach(function (key) { (0, _defineProperty2["default"])(target, key, source[key]); }) : Object.getOwnPropertyDescriptors ? Object.defineProperties(target, Object.getOwnPropertyDescriptors(source)) : ownKeys(Object(source)).forEach(function (key) { Object.defineProperty(target, key, Object.getOwnPropertyDescriptor(source, key)); }); } return target; }

function DateRangePicker(_ref) {
  var label = _ref.label,
      labelAlign = _ref.labelAlign,
      labelWidth = _ref.labelWidth,
      required = _ref.required,
      error = _ref.error,
      _ref$WrapperProps = _ref.WrapperProps,
      WrapperProps = _ref$WrapperProps === void 0 ? {} : _ref$WrapperProps,
      rest = (0, _objectWithoutProperties2["default"])(_ref, _excluded);
  WrapperProps = _objectSpread(_objectSpread({}, WrapperProps), {}, {
    className: (0, _classnames["default"])('cnsui-datepicker', WrapperProps['className'])
  });
  return /*#__PURE__*/_react["default"].createElement(_InputWrapper["default"], (0, _extends2["default"])({
    label: label,
    labelAlign: labelAlign,
    labelWidth: labelWidth,
    required: required,
    error: error
  }, WrapperProps), /*#__PURE__*/_react["default"].createElement(DatePickerWrapper, {
    error: error
  }, /*#__PURE__*/_react["default"].createElement(_DateRangePicker["default"], rest)));
}

var _default = DateRangePicker;
exports["default"] = _default;
var DatePickerWrapper = (0, _base["default"])("div", process.env.NODE_ENV === "production" ? {
  target: "e146vmio0"
} : {
  target: "e146vmio0",
  label: "DatePickerWrapper"
})(function (_ref2) {
  var error = _ref2.error;
  return error && "\n      & svg {\n        fill: #DB2330 !important\n      }\n      & input {\n        color: #DB2330 !important;\n        border-bottom: 1px solid #DB2330 !important;  \n        &::placeholder {\n          color: #DB2330 !important\n        }\n      }\n  ";
}, ";" + (process.env.NODE_ENV === "production" ? "" : "/*# sourceMappingURL=data:application/json;charset=utf-8;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbIi4uLy4uLy4uL3NyYy9jb21wb25lbnRzL0RhdGVSYW5nZVBpY2tlci9EYXRlUmFuZ2VQaWNrZXIudHN4Il0sIm5hbWVzIjpbXSwibWFwcGluZ3MiOiJBQStDeUQiLCJmaWxlIjoiLi4vLi4vLi4vc3JjL2NvbXBvbmVudHMvRGF0ZVJhbmdlUGlja2VyL0RhdGVSYW5nZVBpY2tlci50c3giLCJzb3VyY2VzQ29udGVudCI6WyJpbXBvcnQgc3R5bGVkIGZyb20gJ0BlbW90aW9uL3N0eWxlZCc7XHJcbmltcG9ydCBjbiBmcm9tICdjbGFzc25hbWVzJztcclxuaW1wb3J0IFJlYWN0IGZyb20gJ3JlYWN0JztcclxuaW1wb3J0IFJTdWl0ZURhdGVSYW5nZVBpY2tlciwgeyBEYXRlUmFuZ2VQaWNrZXJQcm9wcyB9IGZyb20gJ3JzdWl0ZS9EYXRlUmFuZ2VQaWNrZXInO1xyXG5pbXBvcnQgSW5wdXRXcmFwcGVyIGZyb20gJy4uL0lucHV0V3JhcHBlcic7XHJcblxyXG5leHBvcnQgdHlwZSBURGF0ZVJhbmdlUGlja2VyID0ge1xyXG4gIGxhYmVsPzogc3RyaW5nO1xyXG4gIGxhYmVsQWxpZ24/OiAnbGVmdCcgfCAncmlnaHQnO1xyXG4gIGxhYmVsV2lkdGg/OiBudW1iZXIgfCBzdHJpbmc7XHJcbiAgcmVxdWlyZWQ/OiBib29sZWFuO1xyXG4gIGVycm9yPzogYm9vbGVhbjtcclxuICBXcmFwcGVyUHJvcHM/OiBSZWNvcmQ8c3RyaW5nLCBhbnk+O1xyXG59ICYgRGF0ZVJhbmdlUGlja2VyUHJvcHM7XHJcblxyXG5mdW5jdGlvbiBEYXRlUmFuZ2VQaWNrZXIoe1xyXG4gIGxhYmVsLFxyXG4gIGxhYmVsQWxpZ24sXHJcbiAgbGFiZWxXaWR0aCxcclxuICByZXF1aXJlZCxcclxuICBlcnJvcixcclxuICBXcmFwcGVyUHJvcHMgPSB7fSxcclxuICAuLi5yZXN0XHJcbn06IFREYXRlUmFuZ2VQaWNrZXIpIHtcclxuICBXcmFwcGVyUHJvcHMgPSB7XHJcbiAgICAuLi5XcmFwcGVyUHJvcHMsXHJcbiAgICBjbGFzc05hbWU6IGNuKCdjbnN1aS1kYXRlcGlja2VyJywgV3JhcHBlclByb3BzWydjbGFzc05hbWUnXSksXHJcbiAgfTtcclxuXHJcbiAgcmV0dXJuIChcclxuICAgIDxJbnB1dFdyYXBwZXJcclxuICAgICAgbGFiZWw9e2xhYmVsfVxyXG4gICAgICBsYWJlbEFsaWduPXtsYWJlbEFsaWdufVxyXG4gICAgICBsYWJlbFdpZHRoPXtsYWJlbFdpZHRofVxyXG4gICAgICByZXF1aXJlZD17cmVxdWlyZWR9XHJcbiAgICAgIGVycm9yPXtlcnJvcn1cclxuICAgICAgey4uLldyYXBwZXJQcm9wc31cclxuICAgID5cclxuICAgICAgPERhdGVQaWNrZXJXcmFwcGVyIGVycm9yPXtlcnJvcn0+XHJcbiAgICAgICAgPFJTdWl0ZURhdGVSYW5nZVBpY2tlciB7Li4ucmVzdH0gLz5cclxuICAgICAgPC9EYXRlUGlja2VyV3JhcHBlcj5cclxuICAgIDwvSW5wdXRXcmFwcGVyPlxyXG4gICk7XHJcbn1cclxuXHJcbmV4cG9ydCBkZWZhdWx0IERhdGVSYW5nZVBpY2tlcjtcclxuXHJcbmNvbnN0IERhdGVQaWNrZXJXcmFwcGVyID0gc3R5bGVkLmRpdjx7IGVycm9yPzogYm9vbGVhbiB9PmBcclxuICAkeyh7IGVycm9yIH0pID0+XHJcbiAgICBlcnJvciAmJlxyXG4gICAgYFxyXG4gICAgICAmIHN2ZyB7XHJcbiAgICAgICAgZmlsbDogI0RCMjMzMCAhaW1wb3J0YW50XHJcbiAgICAgIH1cclxuICAgICAgJiBpbnB1dCB7XHJcbiAgICAgICAgY29sb3I6ICNEQjIzMzAgIWltcG9ydGFudDtcclxuICAgICAgICBib3JkZXItYm90dG9tOiAxcHggc29saWQgI0RCMjMzMCAhaW1wb3J0YW50OyAgXHJcbiAgICAgICAgJjo6cGxhY2Vob2xkZXIge1xyXG4gICAgICAgICAgY29sb3I6ICNEQjIzMzAgIWltcG9ydGFudFxyXG4gICAgICAgIH1cclxuICAgICAgfVxyXG4gIGB9XHJcbmA7XHJcbiJdfQ== */"));