var _interopRequireDefault = require("@babel/runtime/helpers/interopRequireDefault");

var _typeof = require("@babel/runtime/helpers/typeof");

Object.defineProperty(exports, "__esModule", {
  value: true
});
exports["default"] = Modal;

var _extends2 = _interopRequireDefault(require("@babel/runtime/helpers/extends"));

var _base = _interopRequireDefault(require("@emotion/styled/base"));

var React = _interopRequireWildcard(require("react"));

var _Backdrop = _interopRequireDefault(require("@mui/material/Backdrop"));

var _Modal = _interopRequireDefault(require("@mui/material/Modal"));

var _web = require("@react-spring/web");

function _getRequireWildcardCache(nodeInterop) { if (typeof WeakMap !== "function") return null; var cacheBabelInterop = new WeakMap(); var cacheNodeInterop = new WeakMap(); return (_getRequireWildcardCache = function _getRequireWildcardCache(nodeInterop) { return nodeInterop ? cacheNodeInterop : cacheBabelInterop; })(nodeInterop); }

function _interopRequireWildcard(obj, nodeInterop) { if (!nodeInterop && obj && obj.__esModule) { return obj; } if (obj === null || _typeof(obj) !== "object" && typeof obj !== "function") { return { "default": obj }; } var cache = _getRequireWildcardCache(nodeInterop); if (cache && cache.has(obj)) { return cache.get(obj); } var newObj = {}; var hasPropertyDescriptor = Object.defineProperty && Object.getOwnPropertyDescriptor; for (var key in obj) { if (key !== "default" && Object.prototype.hasOwnProperty.call(obj, key)) { var desc = hasPropertyDescriptor ? Object.getOwnPropertyDescriptor(obj, key) : null; if (desc && (desc.get || desc.set)) { Object.defineProperty(newObj, key, desc); } else { newObj[key] = obj[key]; } } } newObj["default"] = obj; if (cache) { cache.set(obj, newObj); } return newObj; }

function _EMOTION_STRINGIFIED_CSS_ERROR__() { return "You have tried to stringify object returned from `css` function. It isn't supposed to be used directly (e.g. as value of the `className` prop), but rather handed to emotion so it can handle it (e.g. as value of `css` prop)."; }

var Fade = /*#__PURE__*/React.forwardRef(function Fade(props, ref) {
  var open = props["in"],
      children = props.children,
      onEnter = props.onEnter,
      onExited = props.onExited,
      duration = props.duration;
  var style = (0, _web.useSpring)({
    from: {
      opacity: 0
    },
    to: {
      opacity: open ? 1 : 0
    },
    config: {
      duration: duration
    },
    onStart: function onStart() {
      if (open && onEnter) {
        onEnter();
      }
    },
    onRest: function onRest() {
      if (!open && onExited) {
        onExited();
      }
    }
  });
  return /*#__PURE__*/React.createElement(_web.animated.div, {
    ref: ref,
    style: style
  }, children);
});

function Modal(_ref) {
  var isOpen = _ref.isOpen,
      onClose = _ref.onClose,
      ModalProps = _ref.ModalProps,
      style = _ref.style,
      children = _ref.children,
      FadeProps = _ref.FadeProps;
  return /*#__PURE__*/React.createElement(_Modal["default"], (0, _extends2["default"])({
    open: isOpen,
    onClose: onClose,
    closeAfterTransition: true,
    BackdropComponent: _Backdrop["default"],
    BackdropProps: {
      timeout: 200
    }
  }, ModalProps), /*#__PURE__*/React.createElement(Fade // in: override 불가
  , (0, _extends2["default"])({
    "in": isOpen // duration: override 가능, default: 100
    ,
    duration: 200
  }, FadeProps), /*#__PURE__*/React.createElement(Window, {
    style: style
  }, children)));
}

var Window = (0, _base["default"])("div", process.env.NODE_ENV === "production" ? {
  target: "e12oarkp0"
} : {
  target: "e12oarkp0",
  label: "Window"
})(process.env.NODE_ENV === "production" ? {
  name: "1f6ytyv",
  styles: "position:absolute;top:50%;left:50%;transform:translate(-50%, -50%);width:auto;min-width:30rem;background-color:#fff;padding:0;border-radius:2rem"
} : {
  name: "1f6ytyv",
  styles: "position:absolute;top:50%;left:50%;transform:translate(-50%, -50%);width:auto;min-width:30rem;background-color:#fff;padding:0;border-radius:2rem",
  map: "/*# sourceMappingURL=data:application/json;charset=utf-8;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbIi4uLy4uLy4uL3NyYy9jb21wb25lbnRzL01vZGFsL01vZGFsLnRzeCJdLCJuYW1lcyI6W10sIm1hcHBpbmdzIjoiQUErRXlCIiwiZmlsZSI6Ii4uLy4uLy4uL3NyYy9jb21wb25lbnRzL01vZGFsL01vZGFsLnRzeCIsInNvdXJjZXNDb250ZW50IjpbImltcG9ydCAqIGFzIFJlYWN0IGZyb20gJ3JlYWN0JztcclxuaW1wb3J0IEJhY2tkcm9wIGZyb20gJ0BtdWkvbWF0ZXJpYWwvQmFja2Ryb3AnO1xyXG5pbXBvcnQgTXVpTW9kYWwsIHsgTW9kYWxQcm9wcyBhcyBNdWlNb2RhbFByb3BzIH0gZnJvbSAnQG11aS9tYXRlcmlhbC9Nb2RhbCc7XHJcbmltcG9ydCB7IHVzZVNwcmluZywgYW5pbWF0ZWQgfSBmcm9tICdAcmVhY3Qtc3ByaW5nL3dlYic7XHJcbmltcG9ydCBzdHlsZWQgZnJvbSAnQGVtb3Rpb24vc3R5bGVkJztcclxuXHJcbmludGVyZmFjZSBGYWRlUHJvcHMge1xyXG4gIGNoaWxkcmVuPzogUmVhY3QuUmVhY3RFbGVtZW50O1xyXG4gIGluOiBib29sZWFuO1xyXG4gIG9uRW50ZXI/OiAoKSA9PiB7fTtcclxuICBvbkV4aXRlZD86ICgpID0+IHt9O1xyXG4gIGR1cmF0aW9uPzogbnVtYmVyO1xyXG59XHJcblxyXG5jb25zdCBGYWRlID0gUmVhY3QuZm9yd2FyZFJlZjxIVE1MRGl2RWxlbWVudCwgRmFkZVByb3BzPihmdW5jdGlvbiBGYWRlKHByb3BzLCByZWYpIHtcclxuICBjb25zdCB7IGluOiBvcGVuLCBjaGlsZHJlbiwgb25FbnRlciwgb25FeGl0ZWQsIGR1cmF0aW9uIH0gPSBwcm9wcztcclxuICBjb25zdCBzdHlsZSA9IHVzZVNwcmluZyh7XHJcbiAgICBmcm9tOiB7IG9wYWNpdHk6IDAgfSxcclxuICAgIHRvOiB7IG9wYWNpdHk6IG9wZW4gPyAxIDogMCB9LFxyXG4gICAgY29uZmlnOiB7IGR1cmF0aW9uIH0sXHJcbiAgICBvblN0YXJ0OiAoKSA9PiB7XHJcbiAgICAgIGlmIChvcGVuICYmIG9uRW50ZXIpIHtcclxuICAgICAgICBvbkVudGVyKCk7XHJcbiAgICAgIH1cclxuICAgIH0sXHJcbiAgICBvblJlc3Q6ICgpID0+IHtcclxuICAgICAgaWYgKCFvcGVuICYmIG9uRXhpdGVkKSB7XHJcbiAgICAgICAgb25FeGl0ZWQoKTtcclxuICAgICAgfVxyXG4gICAgfSxcclxuICB9KTtcclxuXHJcbiAgcmV0dXJuIChcclxuICAgIDxhbmltYXRlZC5kaXYgcmVmPXtyZWZ9IHN0eWxlPXtzdHlsZX0+XHJcbiAgICAgIHtjaGlsZHJlbn1cclxuICAgIDwvYW5pbWF0ZWQuZGl2PlxyXG4gICk7XHJcbn0pO1xyXG5cclxudHlwZSBCYXNlTW9kYWxQcm9wcyA9IFBhcnRpYWw8TXVpTW9kYWxQcm9wcz47XHJcbmV4cG9ydCB0eXBlIFRNb2RhbFByb3BzID0ge1xyXG4gIGlzT3BlbjogYm9vbGVhbjtcclxuICBvbkNsb3NlOiAoKSA9PiB2b2lkO1xyXG4gIHN0eWxlPzogUmVhY3QuQ1NTUHJvcGVydGllcztcclxuICBjaGlsZHJlbjogUmVhY3QuUmVhY3ROb2RlO1xyXG4gIE1vZGFsUHJvcHM/OiBCYXNlTW9kYWxQcm9wcztcclxuICBGYWRlUHJvcHM/OiBPbWl0PEZhZGVQcm9wcywgJ2luJz47XHJcbn07XHJcblxyXG5leHBvcnQgZGVmYXVsdCBmdW5jdGlvbiBNb2RhbCh7XHJcbiAgaXNPcGVuLFxyXG4gIG9uQ2xvc2UsXHJcbiAgTW9kYWxQcm9wcyxcclxuICBzdHlsZSxcclxuICBjaGlsZHJlbixcclxuICBGYWRlUHJvcHMsXHJcbn06IFRNb2RhbFByb3BzKSB7XHJcbiAgcmV0dXJuIChcclxuICAgIDxNdWlNb2RhbFxyXG4gICAgICBvcGVuPXtpc09wZW59XHJcbiAgICAgIG9uQ2xvc2U9e29uQ2xvc2V9XHJcbiAgICAgIGNsb3NlQWZ0ZXJUcmFuc2l0aW9uPXt0cnVlfVxyXG4gICAgICBCYWNrZHJvcENvbXBvbmVudD17QmFja2Ryb3B9XHJcbiAgICAgIEJhY2tkcm9wUHJvcHM9e3sgdGltZW91dDogMjAwIH19XHJcbiAgICAgIHsuLi5Nb2RhbFByb3BzfVxyXG4gICAgPlxyXG4gICAgICA8RmFkZVxyXG4gICAgICAgIC8vIGluOiBvdmVycmlkZSDrtojqsIBcclxuICAgICAgICBpbj17aXNPcGVufVxyXG4gICAgICAgIC8vIGR1cmF0aW9uOiBvdmVycmlkZSDqsIDriqUsIGRlZmF1bHQ6IDEwMFxyXG4gICAgICAgIGR1cmF0aW9uPXsyMDB9XHJcbiAgICAgICAgey4uLkZhZGVQcm9wc31cclxuICAgICAgPlxyXG4gICAgICAgIDxXaW5kb3cgc3R5bGU9e3N0eWxlfT57Y2hpbGRyZW59PC9XaW5kb3c+XHJcbiAgICAgIDwvRmFkZT5cclxuICAgIDwvTXVpTW9kYWw+XHJcbiAgKTtcclxufVxyXG5cclxuY29uc3QgV2luZG93ID0gc3R5bGVkLmRpdmBcclxuICBwb3NpdGlvbjogYWJzb2x1dGU7XHJcbiAgdG9wOiA1MCU7XHJcbiAgbGVmdDogNTAlO1xyXG4gIHRyYW5zZm9ybTogdHJhbnNsYXRlKC01MCUsIC01MCUpO1xyXG4gIHdpZHRoOiBhdXRvO1xyXG4gIG1pbi13aWR0aDogMzByZW07XHJcbiAgYmFja2dyb3VuZC1jb2xvcjogI2ZmZjtcclxuICBwYWRkaW5nOiAwO1xyXG4gIGJvcmRlci1yYWRpdXM6IDJyZW07XHJcbmA7XHJcbiJdfQ== */",
  toString: _EMOTION_STRINGIFIED_CSS_ERROR__
});