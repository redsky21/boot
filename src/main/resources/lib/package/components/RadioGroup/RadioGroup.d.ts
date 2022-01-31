import React from 'react';
declare const RadioGroup: React.ForwardRefExoticComponent<{
    labelAlign?: "left" | "right" | undefined;
    labelWidth?: string | number | undefined;
    label?: string | undefined;
    required?: boolean | undefined;
    error?: boolean | undefined;
    WrapperProps?: Record<string, any> | undefined;
    children?: React.ReactNode;
} & import("./RadioGroup.types").TRadioGroupContext & Omit<React.HTMLAttributes<HTMLDivElement>, "children" | "WrapperProps" | "label" | "error" | "required" | "labelAlign" | "labelWidth" | keyof import("./RadioGroup.types").TRadioGroupContext> & React.RefAttributes<HTMLDivElement>>;
export default RadioGroup;
