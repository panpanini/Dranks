package jp.co.panpanini.dranks.flux

/**
 * ActionCreator dispatches Action to dispatcher.
 * The action's creation may be wrapped into a semantic helper method which sends
 * the action to the dispatcher.
 *
 * For example if you want to update the text in todo-app, you want to create an action with a
 * function signature like `updateText(newText)`. It may be invoked within a user interaction.
 *
 * Make sure Store is subscribed before any action is invoked. Dispatcher does not
 * guarantee dispatching to Store until it is initialized
 */
abstract class ActionCreator<ActionType>(
    val dispatcher: Dispatcher<ActionType>
)