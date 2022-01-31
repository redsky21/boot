import React from 'react';
export declare type TSkeletonProps = {
    variant: 'circle' | 'rect';
    width: React.CSSProperties['width'];
    height: React.CSSProperties['height'];
    delay?: number;
} & React.InsHTMLAttributes<HTMLSpanElement>;
declare const _default: React.MemoExoticComponent<React.ForwardRefExoticComponent<{
    variant: "circle" | "rect";
    width: import("csstype").Property.Width<string | number> | undefined;
    height: import("csstype").Property.Height<string | number> | undefined;
    delay?: number | undefined;
} & React.InsHTMLAttributes<HTMLSpanElement> & React.RefAttributes<HTMLSpanElement>>>;
export default _default;
