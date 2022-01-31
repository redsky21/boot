var _interopRequireDefault = require("@babel/runtime/helpers/interopRequireDefault");

Object.defineProperty(exports, "__esModule", {
  value: true
});
exports["default"] = void 0;

var _base = _interopRequireDefault(require("@emotion/styled/base"));

var Fallback = (0, _base["default"])("div", process.env.NODE_ENV === "production" ? {
  target: "e1bub96e0"
} : {
  target: "e1bub96e0",
  label: "Fallback"
})("position:relative;background:#f5f5f5;text-align:center;p{padding-top:1rem;font-weight:600;color:#222222;}", function (_ref) {
  var size = _ref.size;

  switch (size) {
    case 'small':
      return 'padding: 5rem 2rem; font-size: 1.5rem;';

    default:
      return 'padding: 10rem 2rem; font-size: 1.8rem;';
  }
}, ";" + (process.env.NODE_ENV === "production" ? "" : "/*# sourceMappingURL=data:application/json;charset=utf-8;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbIi4uLy4uLy4uL3NyYy9jb21wb25lbnRzL0ZhbGxiYWNrL0ZhbGxiYWNrLnRzeCJdLCJuYW1lcyI6W10sIm1hcHBpbmdzIjoiQUFFMEQiLCJmaWxlIjoiLi4vLi4vLi4vc3JjL2NvbXBvbmVudHMvRmFsbGJhY2svRmFsbGJhY2sudHN4Iiwic291cmNlc0NvbnRlbnQiOlsiaW1wb3J0IHN0eWxlZCBmcm9tICdAZW1vdGlvbi9zdHlsZWQnO1xyXG5cclxuY29uc3QgRmFsbGJhY2sgPSBzdHlsZWQuZGl2PHsgc2l6ZT86ICdzbWFsbCcgfCAnbWVkaXVtJyB9PmBcclxuICBwb3NpdGlvbjogcmVsYXRpdmU7XHJcbiAgYmFja2dyb3VuZDogI2Y1ZjVmNTtcclxuICB0ZXh0LWFsaWduOiBjZW50ZXI7XHJcblxyXG4gIHAge1xyXG4gICAgcGFkZGluZy10b3A6IDFyZW07XHJcbiAgICBmb250LXdlaWdodDogNjAwO1xyXG4gICAgY29sb3I6ICMyMjIyMjI7XHJcbiAgfVxyXG5cclxuICAkeyh7IHNpemUgfSkgPT4ge1xyXG4gICAgc3dpdGNoIChzaXplKSB7XHJcbiAgICAgIGNhc2UgJ3NtYWxsJzpcclxuICAgICAgICByZXR1cm4gJ3BhZGRpbmc6IDVyZW0gMnJlbTsgZm9udC1zaXplOiAxLjVyZW07JztcclxuICAgICAgZGVmYXVsdDpcclxuICAgICAgICByZXR1cm4gJ3BhZGRpbmc6IDEwcmVtIDJyZW07IGZvbnQtc2l6ZTogMS44cmVtOyc7XHJcbiAgICB9XHJcbiAgfX1cclxuYDtcclxuXHJcbmV4cG9ydCBkZWZhdWx0IEZhbGxiYWNrO1xyXG4iXX0= */"));
var _default = Fallback;
exports["default"] = _default;