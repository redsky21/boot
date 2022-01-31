import { TextFieldProps } from '../TextField';
import React from 'react';
declare type Props = {
    htmlFor?: string;
    children: React.ReactNode;
} & Pick<TextFieldProps, 'label' | 'labelAlign' | 'labelWidth' | 'fullWidth' | 'required' | 'error'>;
export declare type TInputWrapper = Props & Omit<React.HTMLAttributes<HTMLDivElement>, keyof Props>;
declare const InputWrapper: React.ForwardRefExoticComponent<{
    htmlFor?: string | undefined;
    children: React.ReactNode;
} & Pick<TextFieldProps, "fullWidth" | "label" | "error" | "required" | "labelAlign" | "labelWidth"> & Omit<React.HTMLAttributes<HTMLDivElement>, "fullWidth" | "children" | "label" | "error" | "required" | "htmlFor" | "labelAlign" | "labelWidth"> & React.RefAttributes<HTMLDivElement>>;
export default InputWrapper;
