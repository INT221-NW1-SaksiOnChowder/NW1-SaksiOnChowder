const deleteAcc = async (id) => {
    try {
        // const res = await fetch(`http://localhost:5000/announcements/${id}`,
        const res = await fetch(`http://intproj22.sit.kmutt.ac.th:8080/nw1/api/announcements/${id}`,
        { method: 'DELETE' })
        if (res.ok) {
            console.log('Delete Successfully');
        } else {
            throw new Error(`Cannot delete`)
        }
    } catch (err) {
        alert(`Error: ${err}`)
    }
}

export { deleteAcc }