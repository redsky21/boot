import React from 'react';
import { ButtonBaseProps } from '@mui/material/ButtonBase';
export declare type TButtonProps = {
    variant?: 'none' | 'outlined' | 'contained';
    color?: 'primary' | 'primary-reverse' | 'normal' | 'inherit';
    size?: 'xs' | 'sm' | 'md' | 'lg' | 'xl' | 'inherit';
    isLoading?: boolean;
    disabled?: boolean;
    fullWidth?: boolean;
    as?: React.ElementType<any> | undefined;
    to?: string;
    type?: 'button' | 'submit';
    ButtonBaseProps?: ButtonBaseProps;
} & React.HTMLAttributes<HTMLDivElement>;
declare const Button: React.ForwardRefExoticComponent<{
    variant?: "none" | "outlined" | "contained" | undefined;
    color?: "inherit" | "primary" | "primary-reverse" | "normal" | undefined;
    size?: "inherit" | "xs" | "sm" | "md" | "lg" | "xl" | undefined;
    isLoading?: boolean | undefined;
    disabled?: boolean | undefined;
    fullWidth?: boolean | undefined;
    as?: React.ElementType<any> | undefined;
    to?: string | undefined;
    type?: "button" | "submit" | undefined;
    ButtonBaseProps?: ButtonBaseProps<"button", {}> | undefined;
} & React.HTMLAttributes<HTMLDivElement> & React.RefAttributes<HTMLDivElement>>;
export default Button;
export declare const Wrapper: import("@emotion/styled").StyledComponent<{
    theme?: import("@emotion/react").Theme | undefined;
    as?: React.ElementType<any> | undefined;
} & {
    variant?: "none" | "outlined" | "contained" | undefined;
    color?: "inherit" | "primary" | "primary-reverse" | "normal" | undefined;
    size?: "inherit" | "xs" | "sm" | "md" | "lg" | "xl" | undefined;
    isLoading?: boolean | undefined;
    disabled?: boolean | undefined;
    fullWidth?: boolean | undefined;
    as?: React.ElementType<any> | undefined;
    to?: string | undefined;
    type?: "button" | "submit" | undefined;
    ButtonBaseProps?: ButtonBaseProps<"button", {}> | undefined;
} & React.HTMLAttributes<HTMLDivElement>, React.DetailedHTMLProps<React.HTMLAttributes<HTMLDivElement>, HTMLDivElement>, {}>;
