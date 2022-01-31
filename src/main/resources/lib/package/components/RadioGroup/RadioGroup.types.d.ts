import React from 'react';
export declare type TRadioGroupContext = {
    gap?: string | number;
    direction?: 'column' | 'row';
    name?: string;
    value: string | number;
    onChange?: (newVal: string | number) => void;
    hideRadioButton?: boolean;
};
declare type __RadioGroupProps = {
    labelAlign?: 'left' | 'right';
    labelWidth?: number | string;
    label?: string;
    required?: boolean;
    error?: boolean;
    WrapperProps?: Record<string, any>;
    children?: React.ReactNode;
} & TRadioGroupContext;
export declare type TRadioGroupProps = __RadioGroupProps & Omit<React.HTMLAttributes<HTMLDivElement>, keyof __RadioGroupProps>;
export declare type TRadioGroupItemProps = {
    label: React.ReactNode;
    disabled?: boolean;
    value: string | number;
    children?: React.ReactNode;
    hidden?: boolean;
};
export {};
