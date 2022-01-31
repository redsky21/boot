var _interopRequireDefault = require("@babel/runtime/helpers/interopRequireDefault");

Object.defineProperty(exports, "__esModule", {
  value: true
});
exports["default"] = void 0;

var _base = _interopRequireDefault(require("@emotion/styled/base"));

var _react = _interopRequireDefault(require("react"));

var _ = require("..");

function _EMOTION_STRINGIFIED_CSS_ERROR__() { return "You have tried to stringify object returned from `css` function. It isn't supposed to be used directly (e.g. as value of the `className` prop), but rather handed to emotion so it can handle it (e.g. as value of `css` prop)."; }

function ConfirmDialog(_ref) {
  var isOpen = _ref.isOpen,
      onClose = _ref.onClose,
      _ref$onConfirm = _ref.onConfirm,
      onConfirm = _ref$onConfirm === void 0 ? onClose : _ref$onConfirm,
      onCancel = _ref.onCancel,
      _ref$confirmText = _ref.confirmText,
      confirmText = _ref$confirmText === void 0 ? 'Ok' : _ref$confirmText,
      _ref$cancelText = _ref.cancelText,
      cancelText = _ref$cancelText === void 0 ? 'Cancel' : _ref$cancelText,
      children = _ref.children;
  return /*#__PURE__*/_react["default"].createElement(_.Modal, {
    isOpen: isOpen,
    onClose: onClose,
    ModalProps: {
      disableScrollLock: true,
      disableEscapeKeyDown: true
    },
    style: {
      borderRadius: '0.5rem',
      top: '5%',
      transform: 'translate(-50%, 0)'
    }
  }, /*#__PURE__*/_react["default"].createElement(Body, null, children), /*#__PURE__*/_react["default"].createElement(Footer, null, /*#__PURE__*/_react["default"].createElement(_.ElementGroup, {
    gap: 5,
    isAlignedRight: true
  }, onConfirm && /*#__PURE__*/_react["default"].createElement(_.Button, {
    color: "primary",
    onClick: function onClick() {
      return onConfirm(onClose);
    }
  }, confirmText), onCancel && /*#__PURE__*/_react["default"].createElement(_.Button, {
    color: "normal",
    onClick: function onClick() {
      return onCancel(onClose);
    }
  }, cancelText))));
}

var _default = ConfirmDialog;
exports["default"] = _default;
var Body = (0, _base["default"])("div", process.env.NODE_ENV === "production" ? {
  target: "ey33zua1"
} : {
  target: "ey33zua1",
  label: "Body"
})(process.env.NODE_ENV === "production" ? {
  name: "www6pt",
  styles: "min-width:50rem;font-size:1.6rem;padding:1.75rem"
} : {
  name: "www6pt",
  styles: "min-width:50rem;font-size:1.6rem;padding:1.75rem",
  map: "/*# sourceMappingURL=data:application/json;charset=utf-8;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbIi4uLy4uLy4uL3NyYy9jb21wb25lbnRzL0NvbmZpcm1EaWFsb2cvQ29uZmlybURpYWxvZy50c3giXSwibmFtZXMiOltdLCJtYXBwaW5ncyI6IkFBc0R1QiIsImZpbGUiOiIuLi8uLi8uLi9zcmMvY29tcG9uZW50cy9Db25maXJtRGlhbG9nL0NvbmZpcm1EaWFsb2cudHN4Iiwic291cmNlc0NvbnRlbnQiOlsiaW1wb3J0IFJlYWN0IGZyb20gJ3JlYWN0JztcclxuaW1wb3J0IHN0eWxlZCBmcm9tICdAZW1vdGlvbi9zdHlsZWQnO1xyXG5pbXBvcnQgeyBCdXR0b24sIEVsZW1lbnRHcm91cCwgTW9kYWwgfSBmcm9tICcuLic7XHJcblxyXG5leHBvcnQgdHlwZSBUQ29uZmlybURpYWxvZ1Byb3BzID0ge1xyXG4gIGlzT3BlbjogYm9vbGVhbjtcclxuICBvbkNsb3NlOiAoKSA9PiBhbnk7XHJcbiAgY2hpbGRyZW46IFJlYWN0LlJlYWN0Tm9kZTtcclxuICBvbkNvbmZpcm0/OiAob25DbG9zZTogKCkgPT4gYW55KSA9PiBhbnk7XHJcbiAgb25DYW5jZWw/OiAob25DbG9zZTogKCkgPT4gYW55KSA9PiBhbnk7XHJcbiAgY29uZmlybVRleHQ/OiBzdHJpbmc7XHJcbiAgY2FuY2VsVGV4dD86IHN0cmluZztcclxufTtcclxuXHJcbmZ1bmN0aW9uIENvbmZpcm1EaWFsb2coe1xyXG4gIGlzT3BlbixcclxuICBvbkNsb3NlLFxyXG4gIG9uQ29uZmlybSA9IG9uQ2xvc2UsXHJcbiAgb25DYW5jZWwsXHJcbiAgY29uZmlybVRleHQgPSAnT2snLFxyXG4gIGNhbmNlbFRleHQgPSAnQ2FuY2VsJyxcclxuICBjaGlsZHJlbixcclxufTogVENvbmZpcm1EaWFsb2dQcm9wcykge1xyXG4gIHJldHVybiAoXHJcbiAgICA8TW9kYWxcclxuICAgICAgaXNPcGVuPXtpc09wZW59XHJcbiAgICAgIG9uQ2xvc2U9e29uQ2xvc2V9XHJcbiAgICAgIE1vZGFsUHJvcHM9e3tcclxuICAgICAgICBkaXNhYmxlU2Nyb2xsTG9jazogdHJ1ZSxcclxuICAgICAgICBkaXNhYmxlRXNjYXBlS2V5RG93bjogdHJ1ZSxcclxuICAgICAgfX1cclxuICAgICAgc3R5bGU9e3sgYm9yZGVyUmFkaXVzOiAnMC41cmVtJywgdG9wOiAnNSUnLCB0cmFuc2Zvcm06ICd0cmFuc2xhdGUoLTUwJSwgMCknIH19XHJcbiAgICA+XHJcbiAgICAgIDxCb2R5PntjaGlsZHJlbn08L0JvZHk+XHJcbiAgICAgIDxGb290ZXI+XHJcbiAgICAgICAgPEVsZW1lbnRHcm91cCBnYXA9ezV9IGlzQWxpZ25lZFJpZ2h0PXt0cnVlfT5cclxuICAgICAgICAgIHtvbkNvbmZpcm0gJiYgKFxyXG4gICAgICAgICAgICA8QnV0dG9uIGNvbG9yPVwicHJpbWFyeVwiIG9uQ2xpY2s9eygpID0+IG9uQ29uZmlybShvbkNsb3NlKX0+XHJcbiAgICAgICAgICAgICAge2NvbmZpcm1UZXh0fVxyXG4gICAgICAgICAgICA8L0J1dHRvbj5cclxuICAgICAgICAgICl9XHJcbiAgICAgICAgICB7b25DYW5jZWwgJiYgKFxyXG4gICAgICAgICAgICA8QnV0dG9uIGNvbG9yPVwibm9ybWFsXCIgb25DbGljaz17KCkgPT4gb25DYW5jZWwob25DbG9zZSl9PlxyXG4gICAgICAgICAgICAgIHtjYW5jZWxUZXh0fVxyXG4gICAgICAgICAgICA8L0J1dHRvbj5cclxuICAgICAgICAgICl9XHJcbiAgICAgICAgPC9FbGVtZW50R3JvdXA+XHJcbiAgICAgIDwvRm9vdGVyPlxyXG4gICAgPC9Nb2RhbD5cclxuICApO1xyXG59XHJcblxyXG5leHBvcnQgZGVmYXVsdCBDb25maXJtRGlhbG9nO1xyXG5cclxuY29uc3QgQm9keSA9IHN0eWxlZC5kaXZgXHJcbiAgbWluLXdpZHRoOiA1MHJlbTtcclxuICBmb250LXNpemU6IDEuNnJlbTtcclxuICBwYWRkaW5nOiAxLjc1cmVtO1xyXG5gO1xyXG5jb25zdCBGb290ZXIgPSBzdHlsZWQuZGl2YFxyXG4gIHBhZGRpbmc6IDEuNzVyZW07XHJcbmA7XHJcbiJdfQ== */",
  toString: _EMOTION_STRINGIFIED_CSS_ERROR__
});
var Footer = (0, _base["default"])("div", process.env.NODE_ENV === "production" ? {
  target: "ey33zua0"
} : {
  target: "ey33zua0",
  label: "Footer"
})(process.env.NODE_ENV === "production" ? {
  name: "rbm4ha",
  styles: "padding:1.75rem"
} : {
  name: "rbm4ha",
  styles: "padding:1.75rem",
  map: "/*# sourceMappingURL=data:application/json;charset=utf-8;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbIi4uLy4uLy4uL3NyYy9jb21wb25lbnRzL0NvbmZpcm1EaWFsb2cvQ29uZmlybURpYWxvZy50c3giXSwibmFtZXMiOltdLCJtYXBwaW5ncyI6IkFBMkR5QiIsImZpbGUiOiIuLi8uLi8uLi9zcmMvY29tcG9uZW50cy9Db25maXJtRGlhbG9nL0NvbmZpcm1EaWFsb2cudHN4Iiwic291cmNlc0NvbnRlbnQiOlsiaW1wb3J0IFJlYWN0IGZyb20gJ3JlYWN0JztcclxuaW1wb3J0IHN0eWxlZCBmcm9tICdAZW1vdGlvbi9zdHlsZWQnO1xyXG5pbXBvcnQgeyBCdXR0b24sIEVsZW1lbnRHcm91cCwgTW9kYWwgfSBmcm9tICcuLic7XHJcblxyXG5leHBvcnQgdHlwZSBUQ29uZmlybURpYWxvZ1Byb3BzID0ge1xyXG4gIGlzT3BlbjogYm9vbGVhbjtcclxuICBvbkNsb3NlOiAoKSA9PiBhbnk7XHJcbiAgY2hpbGRyZW46IFJlYWN0LlJlYWN0Tm9kZTtcclxuICBvbkNvbmZpcm0/OiAob25DbG9zZTogKCkgPT4gYW55KSA9PiBhbnk7XHJcbiAgb25DYW5jZWw/OiAob25DbG9zZTogKCkgPT4gYW55KSA9PiBhbnk7XHJcbiAgY29uZmlybVRleHQ/OiBzdHJpbmc7XHJcbiAgY2FuY2VsVGV4dD86IHN0cmluZztcclxufTtcclxuXHJcbmZ1bmN0aW9uIENvbmZpcm1EaWFsb2coe1xyXG4gIGlzT3BlbixcclxuICBvbkNsb3NlLFxyXG4gIG9uQ29uZmlybSA9IG9uQ2xvc2UsXHJcbiAgb25DYW5jZWwsXHJcbiAgY29uZmlybVRleHQgPSAnT2snLFxyXG4gIGNhbmNlbFRleHQgPSAnQ2FuY2VsJyxcclxuICBjaGlsZHJlbixcclxufTogVENvbmZpcm1EaWFsb2dQcm9wcykge1xyXG4gIHJldHVybiAoXHJcbiAgICA8TW9kYWxcclxuICAgICAgaXNPcGVuPXtpc09wZW59XHJcbiAgICAgIG9uQ2xvc2U9e29uQ2xvc2V9XHJcbiAgICAgIE1vZGFsUHJvcHM9e3tcclxuICAgICAgICBkaXNhYmxlU2Nyb2xsTG9jazogdHJ1ZSxcclxuICAgICAgICBkaXNhYmxlRXNjYXBlS2V5RG93bjogdHJ1ZSxcclxuICAgICAgfX1cclxuICAgICAgc3R5bGU9e3sgYm9yZGVyUmFkaXVzOiAnMC41cmVtJywgdG9wOiAnNSUnLCB0cmFuc2Zvcm06ICd0cmFuc2xhdGUoLTUwJSwgMCknIH19XHJcbiAgICA+XHJcbiAgICAgIDxCb2R5PntjaGlsZHJlbn08L0JvZHk+XHJcbiAgICAgIDxGb290ZXI+XHJcbiAgICAgICAgPEVsZW1lbnRHcm91cCBnYXA9ezV9IGlzQWxpZ25lZFJpZ2h0PXt0cnVlfT5cclxuICAgICAgICAgIHtvbkNvbmZpcm0gJiYgKFxyXG4gICAgICAgICAgICA8QnV0dG9uIGNvbG9yPVwicHJpbWFyeVwiIG9uQ2xpY2s9eygpID0+IG9uQ29uZmlybShvbkNsb3NlKX0+XHJcbiAgICAgICAgICAgICAge2NvbmZpcm1UZXh0fVxyXG4gICAgICAgICAgICA8L0J1dHRvbj5cclxuICAgICAgICAgICl9XHJcbiAgICAgICAgICB7b25DYW5jZWwgJiYgKFxyXG4gICAgICAgICAgICA8QnV0dG9uIGNvbG9yPVwibm9ybWFsXCIgb25DbGljaz17KCkgPT4gb25DYW5jZWwob25DbG9zZSl9PlxyXG4gICAgICAgICAgICAgIHtjYW5jZWxUZXh0fVxyXG4gICAgICAgICAgICA8L0J1dHRvbj5cclxuICAgICAgICAgICl9XHJcbiAgICAgICAgPC9FbGVtZW50R3JvdXA+XHJcbiAgICAgIDwvRm9vdGVyPlxyXG4gICAgPC9Nb2RhbD5cclxuICApO1xyXG59XHJcblxyXG5leHBvcnQgZGVmYXVsdCBDb25maXJtRGlhbG9nO1xyXG5cclxuY29uc3QgQm9keSA9IHN0eWxlZC5kaXZgXHJcbiAgbWluLXdpZHRoOiA1MHJlbTtcclxuICBmb250LXNpemU6IDEuNnJlbTtcclxuICBwYWRkaW5nOiAxLjc1cmVtO1xyXG5gO1xyXG5jb25zdCBGb290ZXIgPSBzdHlsZWQuZGl2YFxyXG4gIHBhZGRpbmc6IDEuNzVyZW07XHJcbmA7XHJcbiJdfQ== */",
  toString: _EMOTION_STRINGIFIED_CSS_ERROR__
});