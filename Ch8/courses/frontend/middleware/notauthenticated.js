export default function ({ store, redirect }) {
  console.log(store.getters.isAuthenticated)
  if (store.getters.isAuthenticated) {
    return redirect('/dashboard')
  }
}
