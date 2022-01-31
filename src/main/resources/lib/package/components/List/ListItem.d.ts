import { ListItemProps } from '@mui/material/ListItem';
import { ListItemButtonProps } from '@mui/material/ListItemButton';
export declare type TListItemProps = {
    width?: number | string;
} & ListItemProps & ListItemButtonProps;
export default function ListItem({ width, sx, children, onClick, ...rest }: TListItemProps): import("@emotion/react/jsx-runtime").JSX.Element;
