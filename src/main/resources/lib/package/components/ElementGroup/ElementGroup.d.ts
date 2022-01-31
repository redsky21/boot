import React from 'react';
export declare type TElementGroupProps = {
    direction?: 'row' | 'column';
    isAlignedRight?: boolean;
    fullWidth?: boolean;
    gap?: number | string;
    children?: React.ReactNode;
    justifyContent?: React.CSSProperties['justifyContent'];
    alignItems?: React.CSSProperties['alignItems'];
    flexWrap?: React.CSSProperties['flexWrap'];
} & React.HTMLAttributes<HTMLDivElement>;
declare const ElementGroup: React.ForwardRefExoticComponent<{
    direction?: "row" | "column" | undefined;
    isAlignedRight?: boolean | undefined;
    fullWidth?: boolean | undefined;
    gap?: string | number | undefined;
    children?: React.ReactNode;
    justifyContent?: React.CSSProperties['justifyContent'];
    alignItems?: React.CSSProperties['alignItems'];
    flexWrap?: React.CSSProperties['flexWrap'];
} & React.HTMLAttributes<HTMLDivElement> & React.RefAttributes<HTMLDivElement>>;
export default ElementGroup;
