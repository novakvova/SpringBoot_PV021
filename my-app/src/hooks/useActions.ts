import { useDispatch } from 'react-redux';
import { bindActionCreators } from 'redux';
import * as LoginActions from '../components/auth/login/actions';

const actions = {
    ...LoginActions
};

export const useActions = () => {
    const dispatch = useDispatch();
    return  bindActionCreators(actions, dispatch);
}